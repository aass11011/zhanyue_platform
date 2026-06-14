package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
public class StockBehaviourStickyDTO extends BaseDTO {

    /**
    * 主键
    */
    private Long id;

    /**
    * 
    */
    private List<String> fileList;

    /**
    * 
    */
    private String views;


}