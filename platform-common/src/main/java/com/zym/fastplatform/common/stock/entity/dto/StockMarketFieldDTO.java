package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMarketFieldDTO extends BaseDTO {

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
    * 是否必填
    */
    private Boolean isRequired;

    /**
    * 字段选项
    */
    private String fieldOptions;


}