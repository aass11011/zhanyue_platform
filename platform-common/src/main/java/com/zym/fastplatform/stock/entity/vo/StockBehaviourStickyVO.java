package com.zym.fastplatform.stock.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class StockBehaviourStickyVO {

    /**
    * 主键
    */
    private Long id;

    /**
    * 
    */
    private String imageUrl;

    /**
    * 
    */
    private String views;

    private List<String> fileList;
}