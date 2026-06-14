package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockSimulateDTO extends BaseDTO {

    /**
    * 主键
    */
    private Long id;

    /**
    * 股票代码
    */
    private String stockCode;

    /**
    * 股票名称
    */
    private String stockName;

    /**
    * 
    */
    private LocalDate startDate;

    /**
    * 
    */
    private LocalDate endDate;


}