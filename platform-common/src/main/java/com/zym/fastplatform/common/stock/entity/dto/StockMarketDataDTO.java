package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMarketDataDTO extends BaseDTO {

    /**
    * 
    */
    private Integer id;
    private Long recordId;
    /**
    * stock_market_field
    */
    private Long fieldId;

    /**
    * 字段值
    */
    private String fieldValue;


}