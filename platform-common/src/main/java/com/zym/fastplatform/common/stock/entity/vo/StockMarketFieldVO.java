package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMarketFieldVO {

    /**
    * 
    */
    private Long id;

    /**
    * 
    */
    private Long schemaId;

    /**
    * 字段名称
    */
    private String fieldName;

    /**
    * 字段类型id
    */
    private String fieldTypeId;

    /**
    * 
    */
    private Integer sortOrder;

    /**
    * 是否必填 1是 0否
    */
    private Boolean isRequired;

    /**
    * 字段选项
    */
    private String fieldOptions;


}