package com.zym.fastplatform.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class StockTradeRangeAnalysisVO {
    private String stockCode;
    private String stockName;
    private Integer bigBuyCount;
    private Integer bigSellCount;
    private BigDecimal bigBuyAccount;
    private BigDecimal bigSellAccount;
    private Integer smallBuyCount;
    private Integer smallSellCount;
    private BigDecimal smallBuyAccount;
    private BigDecimal smallSellAccount;
    private BigDecimal bigSum;
    private BigDecimal smallSum;
    private List<LocalDate> lackDateList;
    private List<StockTradeDayAnalysisVO> dayAnalysisVOS;
}
