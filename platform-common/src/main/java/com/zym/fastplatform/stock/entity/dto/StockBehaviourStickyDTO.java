package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
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