package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockOptionVO {

    /**
    * 
    */
    private Integer id;

    /**
    * 博主id
    */
    private String viewer;

    /**
    * 观点内容
    */
    private String content;

    /**
    * 观点日期
    */
    private LocalDate dates;

    /**
    * 股票代码
    */
    private String stockCode;

    /**
    * 股票名称
    */
    private String stockName;

    /**
    * 类型
    */
    private String type;
}
