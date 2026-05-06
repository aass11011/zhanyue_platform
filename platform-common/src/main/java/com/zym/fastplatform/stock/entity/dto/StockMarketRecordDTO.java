package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockMarketRecordDTO extends BaseDTO {

    /**
    * 
    */
    private Integer id;

    private String title;
    private Integer schemaId;

    private List<StockMarketDataDTO> list;
}