package com.zym.fastplatform.stock.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PriceDistribution {
    private Long id;
    private String stockCode;      // 股票代码
    private String stockName;      // 股票名称
    private LocalDate tradeDate;   // 交易日期
    private String priceRange;     // 价格区间（如：49.0-49.1）
    private Integer volumeSum;     // 区间总成交量
    private Integer tradeCountSum; // 区间总成交笔数
    private BigDecimal volumeRatio; // 成交量占比（%）
    private BigDecimal avgTradeSize; // 平均每笔成交量
    private Boolean isDenseArea;   // 是否成交密集区
}
