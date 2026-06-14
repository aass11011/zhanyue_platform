package com.zym.fastplatform.common.stock.entity;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "stock_simulate_data")
@Getter
@Setter
public class StockSimulateData extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 股票代码
    */
    @Column
    private String stockCode;

    /**
    * 开盘
    */
    @Column
    private BigDecimal openPrice;

    /**
    * 收盘
    */
    @Column
    private BigDecimal closePrice;

    /**
    * 开盘
    */
    @Column
    private BigDecimal hign;

    /**
    * 最低
    */
    @Column
    private BigDecimal low;

    /**
    * 成交量
    */
    @Column
    private Integer volume;

    /**
    * 成交额
    */
    @Column
    private BigDecimal account;

    /**
    * 振幅
    */
    @Column
    private BigDecimal amplitude;

    /**
    * 涨跌幅
    */
    @Column
    private BigDecimal pricePer;

    /**
    * 涨跌额
    */
    @Column
    private BigDecimal priceChange;

    /**
    * 换手率
    */
    @Column
    private BigDecimal turnover;

    /**
    * 模拟id
    */
    @Column
    private Long simulateId;

    /**
    * 交易日期
    */
    @Column
    private LocalDate tradeDate;


}