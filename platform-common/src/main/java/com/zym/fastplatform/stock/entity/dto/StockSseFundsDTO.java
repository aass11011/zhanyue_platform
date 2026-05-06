package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class StockSseFundsDTO extends BaseDTO {

    /**
    * 
    */
    private Integer id;

    /**
    * 日期
    */
    private LocalDate statDate;

    /**
    * 基金代码
    */
    private String secCode;

    /**
    * 基金名称
    */
    private String secName;

    /**
    * 类型：单市/跨市
    */
    private String etfType;

    /**
    * 总份额（万份）
    */
    private BigDecimal totVol;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}