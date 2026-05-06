package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockCaseDTO extends BaseDTO {

    /**
    * 主键
    */
    private Long id;

    /**
    * 标题
    */
    private String title;

    /**
    * 内容
    */
    private String content;

    /**
    * 股票代码
    */
    private String stockCode;

    /**
    * 股票名称
    */
    private String stockName;

    /**
    * 案例日期
    */
    private LocalDate caseDate;

    /**
    * 案例分类
    */
    private String category;

    /**
    * 标签
    */
    private String tags;

    /**
    * 状态（1：发布，0：编辑中）
    */
    private String status;


}