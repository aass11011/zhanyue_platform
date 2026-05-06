package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StockBehaviourDTO extends BaseDTO {
    /**
    * 
    */
    private String analysis;

    /**
    * 
    */
    private String tradingPlan;

    private String stockCode;

    private List<StockBehaviourStickyDTO> stickFormList;
}