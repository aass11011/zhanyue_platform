package com.zym.fastplatform.stock.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StockBehaviourVO {

    /**
    * 主键
    */
    private Long id;

    /**
    * 
    */
    private String analysis;

    /**
    * 
    */
    private String tradingPlan;

    private String stockCode;

    private List<StockBehaviourStickyVO> stickFormList;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
}