package com.zym.fastplatform.stock.service;

import com.zym.fastplatform.framework.service.BaseService;
import com.zym.fastplatform.stock.entity.StockTradeDetail;
import com.zym.fastplatform.stock.entity.dto.StockTradeDetailDTO;
import com.zym.fastplatform.stock.entity.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface StockTradeDetailService extends BaseService<StockTradeDetail, StockTradeDetailVO, StockTradeDetailDTO> {
    void importData(MultipartFile[] files);

    StockTradeDayAnalysisVO getStockTradeYesterday(String code);

    void importData2(MultipartFile[] files);

    List<StockTradeEchartVO> stockTradeEchartDay(String stockCode, LocalDate date);

    List<StockTradeEchartVO> stockTradeEchartYesterday(String stockCode);

    StockTradeDayAnalysisVO getStockTradeDay(String stockCode, LocalDate date);

    StockTradeRangeAnalysisVO stockTradeRange(String stockCode, LocalDate startDate, LocalDate endDate);

    List<LocalDate> stockTradeDateRangeLack(String stockCode);

    void importDataOne2(MultipartFile file,String stockCode);

    List<LocalDate> stockTradeRangeExist(String stockCode);

    List<TradeDensityVO> getTradeDensity(String stockCode, LocalDate date, Integer interval);
}
