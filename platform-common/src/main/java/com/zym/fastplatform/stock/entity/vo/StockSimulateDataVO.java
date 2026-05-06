package com.zym.fastplatform.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class StockSimulateDataVO {

    /**
    * 主键
    */
    private Long id;

    /**
    * 股票代码
    */
    private String stockCode;

    /**
    * 开盘
    */
    private BigDecimal openPrice;

    /**
    * 收盘
    */
    private BigDecimal closePrice;

    /**
    * 开盘
    */
    private BigDecimal hign;

    /**
    * 最低
    */
    private BigDecimal low;

    /**
    * 成交量
    */
    private Integer volume;

    /**
    * 成交额
    */
    private BigDecimal account;

    /**
    * 振幅
    */
    private BigDecimal amplitude;

    /**
    * 涨跌幅
    */
    private BigDecimal pricePer;

    /**
    * 涨跌额
    */
    private BigDecimal priceChange;

    /**
    * 换手率
    */
    private BigDecimal turnover;

    /**
    * 模拟id
    */
    private Long simulateId;

    /**
    * 交易日期
    */
    private LocalDate tradeDate;


}