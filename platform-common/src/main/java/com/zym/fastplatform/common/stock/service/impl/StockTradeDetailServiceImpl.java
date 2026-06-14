package com.zym.fastplatform.common.stock.service.impl;


import com.zym.fastplatform.common.stock.entity.vo.*;
import com.zym.fastplatform.common.common.util.MinioUtils;
import com.zym.fastplatform.common.common.framework.exception.ZException;
import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.common.framework.utils.DateUtil;
import com.zym.fastplatform.common.common.framework.utils.PictureUtil;
import com.zym.fastplatform.common.common.framework.utils.PinyinUtil;
import com.zym.fastplatform.common.common.framework.utils.StringUtils;
import com.zym.fastplatform.common.stock.convert.StockTradeDayConvertMapper;
import com.zym.fastplatform.common.stock.convert.StockTradeDetailConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockBasicDao;
import com.zym.fastplatform.common.stock.dao.StockTradeDayDao;
import com.zym.fastplatform.common.stock.dao.StockTradeDetailDao;
import com.zym.fastplatform.common.stock.entity.StockBasic;
import com.zym.fastplatform.common.stock.entity.StockTradeDay;
import com.zym.fastplatform.common.stock.entity.StockTradeDetail;
import com.zym.fastplatform.common.stock.entity.dto.StockTradeDetailDTO;
import com.zym.fastplatform.common.stock.entity.vo.*;
import com.zym.fastplatform.common.stock.enums.Direction;
import com.zym.fastplatform.common.stock.service.StockBasicService;
import com.zym.fastplatform.common.stock.service.StockTradeDetailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockTradeDetailServiceImpl extends BaseServiceImpl<StockTradeDetailDao, StockTradeDetail, StockTradeDetailConvertMapper, StockTradeDetailDTO, StockTradeDetailVO> implements StockTradeDetailService {

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("HH:mm"),
            DateTimeFormatter.ofPattern("H:mm")
    };

    @Autowired
    private StockBasicDao stockBasicDao;
    @Autowired
    private StockBasicService stockBasicService;
    @Autowired
    private MinioUtils minioUtils;
    @Autowired
    private StockTradeDayDao stockTradeDayDao;
    @Autowired
    private StockTradeDayConvertMapper stockTradeDayConvertMapper;
    @Override
    @Transactional
    public void importData(MultipartFile[] files){
        for (MultipartFile file : files) {
            importData(file);
        }
    }


    @Transactional
    public void importData(MultipartFile file) {
        try {
            // 读取文本文件内容
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), Charset.forName("GBK")))) {
                List<StockTradeDetail> list = new ArrayList<>();
                String line;
                int lineNumber = 0;
                LocalDate tradeDate = null;
                String stockName = null;
                String stockCode = null;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    lineNumber++;

                    if (lineNumber == 1 && !line.isEmpty()) {
                        // 解析第一行：日期、股票名称、股票代码
                        String[] strings = StringUtils.splitByWhitespace(line);
                        tradeDate = LocalDate.parse(strings[0].trim(), DateTimeFormatter.ofPattern("yyyyMMdd"));
                        stockName = strings[1].trim();
                        stockCode = strings[2].trim().replace("(", "").replace(")", "");
                    } else if (lineNumber >= 4 && !line.isEmpty() && !line.startsWith("#")) {
                        // 解析数据行，跳过表头和注释行
                        String[] stringArray = StringUtils.splitByWhitespace(line);
                        if (stringArray.length >= 4) {
                            StockTradeDetail stockTradeDetail = new StockTradeDetail();
                            for (DateTimeFormatter formatter : FORMATTERS) {
                                try {
                                    stockTradeDetail.setTradeTime(LocalTime.parse(stringArray[0].trim(), formatter));
                                    break;
                                } catch (Exception e) {
                                    // 忽略解析异常，继续尝试下一个格式
                                }
                            }
                            if (stockTradeDetail.getTradeTime() == null){
                                throw new ZException("时间格式错误");
                            }
                            stockTradeDetail.setPrice(new BigDecimal(stringArray[1].trim()));
                            stockTradeDetail.setVolumn(Integer.parseInt(stringArray[2].trim()));
                            stockTradeDetail.setOrders(Integer.parseInt(stringArray[3].trim()));
                            try {
                                stockTradeDetail.setDirection(Direction.valueOf(stringArray[4].trim()));
                            } catch (Exception e){
                                stockTradeDetail.setDirection(Direction.N);
                            }
                            stockTradeDetail.setStockCode(stockCode);
                            stockTradeDetail.setTradeDate(tradeDate);
                            stockTradeDetail.setStockName(stockName);
                            stockTradeDetail.setCreatedBy(getUser().getNickname());
                            list.add(stockTradeDetail);
                        }
                    }
                }

                if (!list.isEmpty()) {
                    if (!stockBasicDao.existsStockBasicByStockCode(stockCode)) {
                        StockBasic stockBasic = new StockBasic();
                        stockBasicService.fillExchangeAndMarket(stockCode, stockBasic);
                        stockBasic.setStockCode(stockCode);
                        stockBasic.setStockFullName(stockName);
                        stockBasic.setStockShortName(PinyinUtil.toUpperFirstLetter(stockName));
                        byte[] logoBytes = PictureUtil.generateLogoBytes(stockBasic.getStockFullName());
                        String fileName = "logo_" + System.currentTimeMillis() + ".png";
                        String logo = minioUtils.upload("img", fileName, "image/png", new ByteArrayInputStream(logoBytes), logoBytes.length);
                        stockBasic.setLogo(logo);
                        stockBasic.setLogoFilename(fileName);
                        stockBasicDao.save(stockBasic);
                    }
                    dao.deleteByStockCodeAndTradeDate(stockCode, tradeDate);
                    dao.saveAll(list);
                    saveStockTradeDay(stockCode, stockName, tradeDate, list);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }

    private void saveStockTradeDay(String stockCode,String stockName, LocalDate tradeDate, List<StockTradeDetail> list) {
        StockTradeDay stockTradeDay = new StockTradeDay();
        stockTradeDay.setStockCode(stockCode);
        stockTradeDay.setStockName(stockName);
        stockTradeDay.setTradeDate(tradeDate);
        stockTradeDay.setCreatedBy(getUser().getNickname());
        stockTradeDay.setBigBuyCount(list.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() >= 500).map(StockTradeDetail::getVolumn).reduce(Integer::sum).orElse(0));
        stockTradeDay.setBigSellCount(list.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() >= 500).map(StockTradeDetail::getVolumn).reduce(Integer::sum).orElse(0));
        stockTradeDay.setBigBuyAccount(list.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() >= 500).map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeDay.setBigSellAccount(list.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() >= 500).map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeDay.setBigSum(list.stream().filter(detail -> detail.getVolumn() >= 500).map(item -> {
            if(item.getDirection().equals(Direction.B)){
                return item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
            }else if (item.getDirection().equals(Direction.S)){
                return item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP).negate();
            }else {
                return BigDecimal.ZERO;
            }
        }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeDay.setSmallBuyCount(list.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() < 500).map(StockTradeDetail::getVolumn).reduce(Integer::sum).orElse(0));
        stockTradeDay.setSmallSellCount(list.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() < 500).map(StockTradeDetail::getVolumn).reduce(Integer::sum).orElse(0));
        stockTradeDay.setSmallBuyAccount(list.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() < 500).map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeDay.setSmallSellAccount(list.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() < 500).map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP)).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeDay.setSmallSum(list.stream().filter(detail -> detail.getVolumn() < 500).map(item -> {
            if(item.getDirection().equals(Direction.B)){
                return item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
            }else if (item.getDirection().equals(Direction.S)){
                return item.getPrice().multiply(BigDecimal.valueOf(item.getVolumn())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP).negate();
            }else {
                return BigDecimal.ZERO;
            }
        }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeDay.setThreshold(500);
        stockTradeDayDao.deleteByStockCodeAndTradeDate(stockCode, tradeDate);
        stockTradeDayDao.save(stockTradeDay);
    }

    @Override
    public StockTradeDayAnalysisVO getStockTradeYesterday(String code) {
        LocalDate yesterday = getYesterday();
        return getStockTradeDay(code, yesterday);
    }
    @Override
    public StockTradeDayAnalysisVO getStockTradeDay(String code, LocalDate date) {
        List<StockTradeDetail> stockTradeDetailList = dao.findByStockCodeAndTradeDate(code, date);
        StockTradeDay stockTradeDay = stockTradeDayDao.findByStockCodeAndTradeDate(code, date);
        StockTradeDayAnalysisVO stockTradeDayAnalysisVO = stockTradeDayConvertMapper.toAnalysisVO(stockTradeDay);
        if (stockTradeDetailList.isEmpty()){
            return stockTradeDayAnalysisVO;
        }
        // 大单
        stockTradeDayAnalysisVO.setBigBuy(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() >= 500).map(StockTradeDetail::getVolumn).toArray(Integer[]::new));
        stockTradeDayAnalysisVO.setBigBuyTime(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() >= 500).map(StockTradeDetail::getTradeTime).toArray(LocalTime[]::new));
        stockTradeDayAnalysisVO.setBigBuyPrice(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() >= 500).map(StockTradeDetail::getPrice).toArray(BigDecimal[]::new));
        stockTradeDayAnalysisVO.setBigSell(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() >= 500).map(StockTradeDetail::getVolumn).toArray(Integer[]::new));
        stockTradeDayAnalysisVO.setBigSellTime(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() >= 500).map(StockTradeDetail::getTradeTime).toArray(LocalTime[]::new));
        stockTradeDayAnalysisVO.setBigSellPrice(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() >= 500).map(StockTradeDetail::getPrice).toArray(BigDecimal[]::new));

        stockTradeDayAnalysisVO.setBigVolume(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() >= 500).map(StockTradeDetail::getVolumn).toArray(Integer[]::new));
        stockTradeDayAnalysisVO.setBigTime(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() >= 500).map(StockTradeDetail::getTradeTime).toArray(LocalTime[]::new));
        stockTradeDayAnalysisVO.setBigPrice(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() >= 500).map(StockTradeDetail::getPrice).toArray(BigDecimal[]::new));
        stockTradeDayAnalysisVO.setBigDirection(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() >= 500).map(StockTradeDetail::getDirection).toArray(Direction[]::new));
        // 小单
        stockTradeDayAnalysisVO.setSmallBuy(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() < 500).map(StockTradeDetail::getVolumn).toArray(Integer[]::new));
        stockTradeDayAnalysisVO.setSmallBuyTime(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() < 500).map(StockTradeDetail::getTradeTime).toArray(LocalTime[]::new));
        stockTradeDayAnalysisVO.setSmallBuyPrice(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.B && detail.getVolumn() < 500).map(StockTradeDetail::getPrice).toArray(BigDecimal[]::new));
        stockTradeDayAnalysisVO.setSmallSell(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() < 500).map(StockTradeDetail::getVolumn).toArray(Integer[]::new));
        stockTradeDayAnalysisVO.setSmallSellTime(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() < 500).map(StockTradeDetail::getTradeTime).toArray(LocalTime[]::new));
        stockTradeDayAnalysisVO.setSmallSellPrice(stockTradeDetailList.stream().filter(detail -> detail.getDirection() == Direction.S && detail.getVolumn() < 500).map(StockTradeDetail::getPrice).toArray(BigDecimal[]::new));

        stockTradeDayAnalysisVO.setSmallVolume(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() < 500).map(StockTradeDetail::getVolumn).toArray(Integer[]::new));
        stockTradeDayAnalysisVO.setSmallTime(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() < 500).map(StockTradeDetail::getTradeTime).toArray(LocalTime[]::new));
        stockTradeDayAnalysisVO.setSmallPrice(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() < 500).map(StockTradeDetail::getPrice).toArray(BigDecimal[]::new));
        stockTradeDayAnalysisVO.setSmallDirection(stockTradeDetailList.stream().filter(detail -> detail.getVolumn() < 500).map(StockTradeDetail::getDirection).toArray(Direction[]::new));

        return stockTradeDayAnalysisVO;
    }


    @Override
    public StockTradeRangeAnalysisVO stockTradeRange(String stockCode, LocalDate startDate, LocalDate endDate) {
        List<StockTradeDay> stockTradeDayList = stockTradeDayDao.findByStockCodeAndTradeDateBetween(stockCode,startDate,endDate);
        if (stockTradeDayList.isEmpty()){
            return null;
        }
        StockTradeRangeAnalysisVO stockTradeRangeAnalysisVO = new StockTradeRangeAnalysisVO();
        Set<LocalDate> tradeDateSet = stockTradeDayList.stream().map(StockTradeDay::getTradeDate).collect(Collectors.toSet());
        List<LocalDate> lackDateList = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if(!tradeDateSet.contains(date)&& !DateUtil.isWeekend(date)){
                lackDateList.add(date);
            }
        }
        stockTradeRangeAnalysisVO.setLackDateList(lackDateList);
        List<StockTradeDayAnalysisVO> analysisVOList = stockTradeDayConvertMapper.toAnalysisVOList(stockTradeDayList);
        stockTradeRangeAnalysisVO.setDayAnalysisVOS(analysisVOList);
        stockTradeRangeAnalysisVO.setStockCode(stockCode);
        stockTradeRangeAnalysisVO.setStockName(stockTradeDayList.get(0).getStockName());
        stockTradeRangeAnalysisVO.setBigBuyCount(stockTradeDayList.stream().map(StockTradeDay::getBigBuyCount).reduce(Integer::sum).orElse(0));
        stockTradeRangeAnalysisVO.setBigSellCount(stockTradeDayList.stream().map(StockTradeDay::getBigSellCount).reduce(Integer::sum).orElse(0));
        stockTradeRangeAnalysisVO.setBigBuyAccount(stockTradeDayList.stream().map(StockTradeDay::getBigBuyAccount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeRangeAnalysisVO.setBigSellAccount(stockTradeDayList.stream().map(StockTradeDay::getBigSellAccount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeRangeAnalysisVO.setSmallBuyCount(stockTradeDayList.stream().map(StockTradeDay::getSmallBuyCount).reduce(Integer::sum).orElse(0));
        stockTradeRangeAnalysisVO.setSmallSellCount(stockTradeDayList.stream().map(StockTradeDay::getSmallSellCount).reduce(Integer::sum).orElse(0));
        stockTradeRangeAnalysisVO.setSmallBuyAccount(stockTradeDayList.stream().map(StockTradeDay::getSmallBuyAccount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeRangeAnalysisVO.setSmallSellAccount(stockTradeDayList.stream().map(StockTradeDay::getSmallSellAccount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeRangeAnalysisVO.setBigSum(stockTradeDayList.stream().map(StockTradeDay::getBigSum).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        stockTradeRangeAnalysisVO.setSmallSum(stockTradeDayList.stream().map(StockTradeDay::getSmallSum).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        return stockTradeRangeAnalysisVO;
    }

    @Override
    public List<LocalDate> stockTradeDateRangeLack(String stockCode) {
        LocalDate now = LocalDate.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        LocalDate start = null;
        if(dayOfWeek > 5){ //周六日
            start = now.minusWeeks(2).with(DayOfWeek.MONDAY);
        }else { //周一到周五
            start = now.minusWeeks(3).with(DayOfWeek.MONDAY);
        }
        List<StockTradeDay> stockTradeDayList = stockTradeDayDao.findByStockCodeAndTradeDateBetween(stockCode,start,now);
        Set<LocalDate> tradeDateSet = stockTradeDayList.stream().map(StockTradeDay::getTradeDate).collect(Collectors.toSet());
        List<LocalDate> lackDateList = new ArrayList<>();
        for (LocalDate date = start; !date.isAfter(now); date = date.plusDays(1)) {
            if(!tradeDateSet.contains(date)&& !DateUtil.isWeekend(date)){
                lackDateList.add(date);
            }
        }
        return lackDateList;
    }

    @Override
    public void importDataOne2(MultipartFile file,String stockCode) {
        importData2(file,stockCode);
    }

    @Override
    public List<LocalDate> stockTradeRangeExist(String stockCode) {
        LocalDate now = LocalDate.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        LocalDate start = null;
        if(dayOfWeek > 5){ //周六日
            start = now.minusWeeks(2).with(DayOfWeek.MONDAY);
        }else { //周一到周五
            start = now.minusWeeks(3).with(DayOfWeek.MONDAY);
        }
        List<StockTradeDay> stockTradeDayList = stockTradeDayDao.findByStockCodeAndTradeDateBetween(stockCode,start,now);
        return stockTradeDayList.stream().map(StockTradeDay::getTradeDate).toList();
    }

    @Override
    public List<TradeDensityVO> getTradeDensity(String stockCode, LocalDate date, Integer interval) {
        List<StockTradeDetail> stockTradeDetailList = dao.findByStockCodeAndTradeDate(stockCode,date);
        if(stockTradeDetailList.isEmpty()){
            return new ArrayList<>();
        }
        stockTradeDetailList = stockTradeDetailList.stream().sorted(Comparator.comparing(StockTradeDetail::getTradeTime)).toList();
        List<TradeDensityVO> resultList = new ArrayList<>();
        int total = stockTradeDetailList.size();
        boolean hasOrder = stockTradeDetailList.get(0).getOrders() != null;
        List<BigDecimal> densityList = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            List<StockTradeDetail> subList = stockTradeDetailList.subList(i, Math.min(i + 5, total - 1));
            int totalVolume = subList.stream().mapToInt(StockTradeDetail::getVolumn).sum();
            int totalOrderCount = subList.stream().mapToInt(StockTradeDetail::getOrders).sum();
            BigDecimal density;
            if(hasOrder){
                density = new BigDecimal(totalVolume).divide(new BigDecimal(subList.size()), 2, RoundingMode.HALF_UP);
            }else {
                density = new BigDecimal(totalVolume).divide(new BigDecimal(totalOrderCount), 2, RoundingMode.HALF_UP);
            }
            densityList.add(density);
        }
        for (int i = 0; i < densityList.size(); i++) {

        }
        return resultList;
    }

    @Override
    public List<StockTradeDay> getStockTradeDayTwoWeekLatest(String stockCode) {
        List<StockTradeDay> stockTradeDayList = stockTradeDayDao.findByStockCodeAndTradeDateBetween(stockCode, LocalDate.now().minusWeeks(2), LocalDate.now());
        return stockTradeDayList;
    }

    @NotNull
    private static LocalDate getYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        DayOfWeek dayOfWeek = yesterday.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY){
            yesterday = yesterday.minusDays(1);
        } else if (dayOfWeek == DayOfWeek.SUNDAY){
            yesterday = yesterday.minusDays(2);
        }
        return yesterday;
    }

    @Override
    public void importData2(MultipartFile[] files) {
        for (MultipartFile file : files) {
            importData2(file,null);
        }
    }

    @Override
    public List<StockTradeEchartVO> stockTradeEchartDay(String stockCode, LocalDate date) {
        List<StockTradeDetail> stockTradeDetailList = dao.findAll(Example.of(StockTradeDetail.builder().stockCode(stockCode).tradeDate(date).build()));
        return stockTradeDetailList.stream().map(item -> new StockTradeEchartVO(item.getTradeTime(), item.getVolumn())).collect(Collectors.toList());
    }

    @Override
    public List<StockTradeEchartVO> stockTradeEchartYesterday(String stockCode) {
        LocalDate yesterday = getYesterday();
        return stockTradeEchartDay(stockCode, yesterday);
    }


    private void importData2(MultipartFile file,String stockCode) {
        try {
            // 读取文本文件内容
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), Charset.forName("GBK")))) {
                List<StockTradeDetail> list = new ArrayList<>();
                String line;
                int lineNumber = 0;
                String stockName = null;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    lineNumber++;

                    if (lineNumber == 1 && !line.isEmpty()) {
                        // 解析第一行：日期、股票名称、股票代码
                        String[] strings = StringUtils.splitByWhitespace(line);
                        stockName = strings[0].trim();
                        if(StringUtils.isEmpty(stockCode)){
                            stockCode = strings[1].trim().replace("(", "").replace(")", "");
                        }
                    } else if (lineNumber >= 4 && !line.isEmpty() && !line.startsWith("#")) {
                        // 解析数据行，跳过表头和注释行
                        String[] stringArray = StringUtils.splitByWhitespace(line);
                        if (stringArray.length >= 4) {
                            StockTradeDetail stockTradeDetail = new StockTradeDetail();
                            MonthDay monthDay = MonthDay.parse(stringArray[0].trim(), DateTimeFormatter.ofPattern("MM/dd"));
                            LocalDate tradeDate = LocalDate.of(LocalDate.now().getYear(), monthDay.getMonthValue(), monthDay.getDayOfMonth());
                            stockTradeDetail.setTradeDate(tradeDate);
                            for (DateTimeFormatter formatter : FORMATTERS) {
                                try {
                                    stockTradeDetail.setTradeTime(LocalTime.parse(stringArray[1].trim(), formatter));
                                    break;
                                } catch (Exception e) {
                                    // 忽略解析异常，继续尝试下一个格式
                                }
                            }
                            if (stockTradeDetail.getTradeTime() == null){
                                throw new ZException("时间格式错误");
                            }
                            stockTradeDetail.setPrice(new BigDecimal(stringArray[2].trim()));
                            stockTradeDetail.setVolumn(Integer.parseInt(stringArray[3].trim()));
//                            stockTradeDetail.setOrders(Integer.parseInt(stringArray[3].trim()));
                            if(stringArray[4].trim().contains("买")){
                                stockTradeDetail.setDirection(Direction.B);
                            }else if (stringArray[4].trim().contains("卖")){
                                stockTradeDetail.setDirection(Direction.S);
                            }else {
                                stockTradeDetail.setDirection(Direction.N);
                            }
                            stockTradeDetail.setStockCode(stockCode);
                            stockTradeDetail.setStockName(stockName);
                            stockTradeDetail.setCreatedBy(getUser().getNickname());
                            list.add(stockTradeDetail);
                        }
                    }
                }

                if (!list.isEmpty()) {
                    if (!stockBasicDao.existsStockBasicByStockCode(stockCode)) {
                        StockBasic stockBasic = new StockBasic();
                        stockBasicService.fillExchangeAndMarket(stockCode, stockBasic);
                        stockBasic.setStockCode(stockCode);
                        stockBasic.setStockFullName(stockName);
                        stockBasic.setStockShortName(PinyinUtil.toUpperFirstLetter(stockName));
                        byte[] logoBytes = PictureUtil.generateLogoBytes(stockBasic.getStockFullName());
                        String fileName = "logo_" + System.currentTimeMillis() + ".png";
                        String logo = minioUtils.upload("img", fileName, "image/png", new ByteArrayInputStream(logoBytes), logoBytes.length);
                        stockBasic.setLogo(logo);
                        stockBasic.setLogoFilename(fileName);
                        stockBasicDao.save(stockBasic);
                    }
                    dao.deleteByStockCodeAndTradeDate(stockCode, list.get(0).getTradeDate());
                    dao.saveAll(list);
                    saveStockTradeDay(stockCode, stockName, list.get(0).getTradeDate(), list);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }
}