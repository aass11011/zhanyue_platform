package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockSimulateVO {

    /**
    * 主键
    */
    private Long id;

    /**
    * 股票代码
    */
    private String stockCode;

    /**
    * 股票名称
    */
    private String stockName;

    /**
    * 
    */
    private LocalDate startDate;

    /**
    * 
    */
    private LocalDate endDate;


}