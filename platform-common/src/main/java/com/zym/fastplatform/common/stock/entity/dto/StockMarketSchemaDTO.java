package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockMarketSchemaDTO extends BaseDTO {

    /**
    * 
    */
    private Long id;

    /**
    * 模板名称
    */
    private String name;

    private List<StockMarketFieldDTO> fields;
}
