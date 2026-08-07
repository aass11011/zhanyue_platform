// 文件路径: src/main/java/com/zym/fastplatform/stock/service/impl/StockBasicServiceImpl.java
package com.zym.fastplatform.common.stock.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.zym.fastplatform.common.common.util.MinioUtils;
import com.zym.fastplatform.common.common.framework.exception.ZException;
import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.common.framework.utils.PictureUtil;
import com.zym.fastplatform.common.common.framework.utils.StringUtils;
import com.zym.fastplatform.common.common.framework.utils.PinyinUtil;
import com.zym.fastplatform.common.stock.convert.StockBasicConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockBasicDao;
import com.zym.fastplatform.common.stock.dao.StockConceptRelDao;
import com.zym.fastplatform.common.stock.entity.StockBasic;
import com.zym.fastplatform.common.stock.entity.StockConceptRel;
import com.zym.fastplatform.common.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBasicVO;
import com.zym.fastplatform.common.stock.service.StockBasicService;
import com.zym.fastplatform.common.system.dao.SysDictDataDao;
import com.zym.fastplatform.common.system.entity.SysDictData;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static com.zym.fastplatform.common.common.util.JpaUtils.parseSort;

@Slf4j
@Service
public class StockBasicServiceImpl extends BaseServiceImpl<StockBasicDao, StockBasic, StockBasicConvertMapper, StockBasicDTO, StockBasicVO> implements StockBasicService {
    @Autowired
    private SysDictDataDao sysDictDataDao;
    @Autowired
    private MinioUtils minioUtils;
    @Autowired
    private StockConceptRelDao stockConceptRelDao;

    @Override
    public Page<StockBasicVO> find(String sort, StockBasicDTO condition) {
        StockBasic stockBasic = convertMapper.toEntity(condition);

        Specification<StockBasic> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(stockBasic.getStockCode())) {
                predicates.add(cb.equal(root.get("stockCode"), stockBasic.getStockCode()));
            }
            if (StringUtils.isNotBlank(stockBasic.getExchange())) {
                predicates.add(cb.equal(root.get("exchange"), stockBasic.getExchange()));
            }
            if (StringUtils.isNotBlank(stockBasic.getMarketType())) {
                predicates.add(cb.equal(root.get("marketType"), stockBasic.getMarketType()));
            }
            if (StringUtils.isNotBlank(stockBasic.getIndustry())) {
                predicates.add(cb.equal(root.get("industry"), stockBasic.getIndustry()));
            }
            if (StringUtils.isNotBlank(stockBasic.getStockShortName())) {
                predicates.add(cb.like(root.get("stockShortName"), "%" + stockBasic.getStockShortName() + "%"));
            }
            if (StringUtils.isNotBlank(stockBasic.getStockFullName())) {
                predicates.add(cb.like(root.get("stockFullName"), "%" + stockBasic.getStockFullName() + "%"));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sortObj = parseSort(sort);
        PageRequest pageRequest = PageRequest.of(condition.getPageIndex(), condition.getPageSize(), sortObj);
        Page<StockBasic> res = dao.findAll(spec, pageRequest);

        List<String> stockCodes = res.getContent().stream()
                .map(StockBasic::getStockCode)
                .collect(Collectors.toList());

        if (!stockCodes.isEmpty()) {
            List<StockConceptRel> concepts = stockConceptRelDao.findByStockCodeIn(stockCodes);
            Map<String, List<StockConceptRel>> conceptsByStockCode = concepts.stream()
                    .collect(Collectors.groupingBy(StockConceptRel::getStockCode));

            for (StockBasic stock : res.getContent()) {
                stock.setStockConceptList(conceptsByStockCode.getOrDefault(stock.getStockCode(), Collections.emptyList()));
            }
        }

        return convertMapper.toVOPage(res);
    }

    @Override
    @Transactional
    public void save(StockBasicDTO dto) {
        StockBasic entity = convertMapper.toEntity(dto);
        String stockCode = entity.getStockCode();
        if(entity.getId()== null){
            StockBasic stockBasic = dao.findByStockCode(stockCode);
            if(stockBasic != null){
                throw new ZException("股票代码已存在");
            }
        }
        entity.setStockShortName(PinyinUtil.toUpperFirstLetter(entity.getStockFullName()));
        fillExchangeAndMarket(stockCode, entity);
        if(StringUtils.isEmpty(entity.getLogo())){
            byte[] logoBytes = PictureUtil.generateLogoBytes(entity.getStockFullName());
            String fileName = "logo_" + System.currentTimeMillis() + ".png";
            String logoUrl = minioUtils.upload("img",fileName, "image/png", new ByteArrayInputStream(logoBytes),logoBytes.length);
            entity.setLogo(logoUrl);
        }
        dao.save(entity);
        saveConcepts(stockCode, dto.getStockConceptList());
    }

    private void saveConcepts(String stockCode, List<StockConceptRel> stockConceptRelList) {
        stockConceptRelDao.deleteByStockCode(stockCode);
        stockConceptRelDao.saveAll(stockConceptRelList);
    }

    public void fillDictDataAboutConcept(List<String> conceptList) {
        for (String concept : conceptList) {
            SysDictData sysDictData = sysDictDataDao.findByDictNameAndTypeId(concept, "a7aaaa5a-bf17-4609-9c1d-5f7de719e478");
            if(sysDictData == null){
                sysDictData = new SysDictData();
                sysDictData.setDictName(concept);
                sysDictData.setDictType("股票概念");
                sysDictData.setTypeId("a7aaaa5a-bf17-4609-9c1d-5f7de719e478");
                sysDictData.setSortOrder(0);
                sysDictDataDao.save(sysDictData);
            }
        }
    }
    @Override
    public void fillExchangeAndMarket(String stockCode, StockBasic entity) {
        if(stockCode.startsWith("6")){
            entity.setExchange("上交所");
            if (stockCode.startsWith("60")){
                entity.setMarketType("主板");
            } else if (stockCode.startsWith("688")) {
                entity.setMarketType("科创板");
            }
        }else if(stockCode.startsWith("0")){
            entity.setExchange("深交所");
            entity.setMarketType("主板");
        } else if (stockCode.startsWith("30")) {
            entity.setExchange("深交所");
            entity.setMarketType("创业板");
        } else if (stockCode.startsWith("920")) {
            entity.setExchange("上交所");
        } else {
            log.error("股票代码格式错误:{}",stockCode);
            throw new ZException("股票代码格式错误");
        }
    }

    @Override
    @Transactional
    public void getByStockCode(String stockCode) {
        StockBasic stockBasic = dao.findByStockCode(stockCode);
        
        if (stockBasic == null) {
            try {
                String marketPrefix = stockCode.startsWith("6") ? "sh" : "sz";
                String url = "http://hq.sinajs.cn/list=" + marketPrefix + stockCode;
                
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("User-Agent", "Mozilla/5.0")
                        .GET()
                        .build();
                
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
                
                if (response.statusCode() != 200) {
                    throw new ZException("获取股票信息失败，HTTP状态码: " + response.statusCode());
                }
                
                String result = response.body();
                if (result == null || result.isEmpty()) {
                    throw new ZException("获取股票信息失败，返回数据为空");
                }
                
                String stockInfo = parseSinaStockData(result);
                if (stockInfo == null) {
                    throw new ZException("获取股票信息失败，无法解析数据");
                }
                
                String[] data = stockInfo.split(",");
                if (data.length < 2) {
                    throw new ZException("获取股票信息失败，数据格式错误");
                }
                
                stockBasic = new StockBasic();
                stockBasic.setStockCode(stockCode);
                stockBasic.setStockFullName(data[0].trim());
                stockBasic.setStockShortName(PinyinUtil.toUpperFirstLetter(data[0].trim()));
                stockBasic.setIndustry(getIndustryByStockCode(stockCode));
                
                fillExchangeAndMarket(stockCode, stockBasic);
                
                byte[] logoBytes = PictureUtil.generateLogoBytes(stockBasic.getStockFullName());
                String fileName = "logo_" + System.currentTimeMillis() + ".png";
                String logoUrl = minioUtils.upload("img", fileName, "image/png", new ByteArrayInputStream(logoBytes), logoBytes.length);
                stockBasic.setLogo(logoUrl);
                
                dao.save(stockBasic);
            } catch (ZException e) {
                throw e;
            } catch (Exception e) {
                log.error("获取股票信息失败: {}", e.getMessage());
                throw new ZException("获取股票信息失败: " + e.getMessage());
            }
        }
    }
    
    private String parseSinaStockData(String response) {
        int startIndex = response.indexOf("\"");
        int endIndex = response.lastIndexOf("\"");
        if (startIndex >= 0 && endIndex > startIndex) {
            return response.substring(startIndex + 1, endIndex);
        }
        return null;
    }
    
    private String getIndustryByStockCode(String stockCode) {
        if (stockCode.startsWith("600") || stockCode.startsWith("601") || stockCode.startsWith("603") || stockCode.startsWith("605")) {
            return "沪市A股";
        } else if (stockCode.startsWith("000")) {
            return "深市主板";
        } else if (stockCode.startsWith("002")) {
            return "深市中小板";
        } else if (stockCode.startsWith("300")) {
            return "创业板";
        } else if (stockCode.startsWith("688")) {
            return "科创板";
        } else {
            return "其他";
        }
    }

    @Transactional
    public void saveAll(List<StockBasic> list) {
        for (StockBasic entity : list) {
            fillExchangeAndMarket(entity.getStockCode(), entity);
            if (!entity.getConcept().isEmpty()){
                fillDictDataAboutConcept(entity.getConcept());
            }
        }
        dao.saveAll(list);
//        for (StockBasic entity : list) {
//            saveConcepts(entity.getStockCode(), entity.getStockConceptList());
//        }
    }

    private class StockBasicExcelListener extends AnalysisEventListener<StockBasicDTO> {
        @Getter
        private final List<StockBasic> list = new ArrayList<>();
        @Override
        public void invoke(StockBasicDTO stockBasic, AnalysisContext analysisContext) {
            StockBasic entity = convertMapper.toEntity(stockBasic);
            list.add(entity);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        }
    }


    @Override
    @Transactional
    public void importData(MultipartFile file) {
        try {
            // 创建监听器
            StockBasicExcelListener listener = new StockBasicExcelListener();

            // 构建Excel读取器
            ExcelReaderBuilder readerBuilder = EasyExcel.read(file.getInputStream(), StockBasic.class, listener);
            ExcelReaderSheetBuilder sheetBuilder = readerBuilder.sheet();

            // 读取Excel数据
            sheetBuilder.doRead();

            // 获取解析后的数据
            List<StockBasic> stockBasicList = listener.getList();
            saveAll(stockBasicList);
            // 批量保存数据

        } catch (IOException e) {
            throw new ZException("Excel文件读取失败：" + e.getMessage());
        }
    }

    @Override
    public void exportData(ServletOutputStream outputStream,StockBasicDTO stockBasicDTO) {
        Specification<StockBasic> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(stockBasicDTO.getStockCode())){
                predicates.add(cb.like(root.get("stockCode"), "%" + stockBasicDTO.getStockCode() + "%"));
            }
            // 根据股票简称查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getStockShortName())) {
                predicates.add(cb.like(root.get("stockShortName"), "%" + stockBasicDTO.getStockShortName() + "%"));
            }

            // 根据交易所查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getExchange())) {
                predicates.add(cb.equal(root.get("exchange"), stockBasicDTO.getExchange()));
            }

            // 根据市场类型查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getMarketType())) {
                predicates.add(cb.equal(root.get("marketType"), stockBasicDTO.getMarketType()));
            }

            // 根据行业查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getIndustry())) {
                predicates.add(cb.like(root.get("industry"), "%" + stockBasicDTO.getIndustry() + "%"));
            }
            if(predicates.isEmpty()){
                return null;
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };

        List<StockBasic> list = dao.findAll(specification);
        EasyExcel.write(outputStream, StockBasic.class).sheet("股票基本信息").doWrite(list);
    }

    @Override
    public Map<String, List<StockBasicVO>> groupByLeadingStockConcept(String keyword) {
        List<StockBasic> allStocks = dao.findAll();

        List<String> stockCodes = allStocks.stream()
                .map(StockBasic::getStockCode)
                .collect(Collectors.toList());

        Map<String, List<StockConceptRel>> conceptsByStockCode = Collections.emptyMap();
        if (!stockCodes.isEmpty()) {
            List<StockConceptRel> concepts = stockConceptRelDao.findByStockCodeIn(stockCodes);
            conceptsByStockCode = concepts.stream()
                    .filter(sc -> Boolean.TRUE.equals(sc.getLeadingFlag()))
                    .collect(Collectors.groupingBy(StockConceptRel::getStockCode));
        }

        Map<String, List<StockBasicVO>> result = new LinkedHashMap<>();

        for (StockBasic stock : allStocks) {
            List<StockConceptRel> stockConceptRels = conceptsByStockCode.getOrDefault(stock.getStockCode(), Collections.emptyList());
            for (StockConceptRel sc : stockConceptRels) {
                String trimmedConcept = sc.getConcept().trim();
                if (StringUtils.isBlank(trimmedConcept)) {
                    continue;
                }
                if (StringUtils.isNotBlank(keyword) && !trimmedConcept.contains(keyword.trim())) {
                    continue;
                }
                result.computeIfAbsent(trimmedConcept, k -> new ArrayList<>())
                        .add(convertMapper.toVO(stock));
            }
        }

        return result;
    }

    @Override
    public Map<String, List<StockBasicVO>> groupByConcept(String keyword) {
        List<StockBasic> allStocks = dao.findAll();

        List<String> stockCodes = allStocks.stream()
                .map(StockBasic::getStockCode)
                .collect(Collectors.toList());

        Map<String, List<StockConceptRel>> conceptsByStockCode = Collections.emptyMap();
        if (!stockCodes.isEmpty()) {
            List<StockConceptRel> concepts = stockConceptRelDao.findByStockCodeIn(stockCodes);
            conceptsByStockCode = concepts.stream()
                    .filter(sc -> !Boolean.TRUE.equals(sc.getLeadingFlag()))
                    .collect(Collectors.groupingBy(StockConceptRel::getStockCode));
        }

        Map<String, List<StockBasicVO>> result = new LinkedHashMap<>();

        for (StockBasic stock : allStocks) {
            List<StockConceptRel> stockConceptRels = conceptsByStockCode.getOrDefault(stock.getStockCode(), Collections.emptyList());
            for (StockConceptRel sc : stockConceptRels) {
                String trimmedConcept = sc.getConcept().trim();
                if (StringUtils.isBlank(trimmedConcept)) {
                    continue;
                }
                if (StringUtils.isNotBlank(keyword) && !trimmedConcept.contains(keyword.trim())) {
                    continue;
                }
                result.computeIfAbsent(trimmedConcept, k -> new ArrayList<>())
                        .add(convertMapper.toVO(stock));
            }
        }

        return result;
    }
}