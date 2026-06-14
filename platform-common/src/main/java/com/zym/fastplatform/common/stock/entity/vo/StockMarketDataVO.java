package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMarketDataVO {

    /**
    * 
    */
    private Long id;
    private Long recordId;
    /**
    * stock_market_field
    */
    private Long fieldId;

    /**
    * 字段值
    */
    private String fieldValue;

    private String fieldName;
}
