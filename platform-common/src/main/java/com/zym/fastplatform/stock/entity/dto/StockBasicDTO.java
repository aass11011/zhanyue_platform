package com.zym.fastplatform.stock.entity.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class StockBasicDTO extends BaseDTO {
    @ExcelIgnore
    private Long id;
    @ExcelProperty("股票代码")
    private String stockCode;
    @ExcelProperty("股票简称")
    private String stockShortName;
    @ExcelProperty("股票全称")
    private String stockFullName;
    private String exchange;
    private String marketType;
    @ExcelProperty("行业")
    private String industry;
    @ExcelProperty("上市日期")
    @DateTimeFormat("yyyy-MM-dd")
    private LocalDate listingDate;
    @ExcelProperty("总股本")
    private BigDecimal totalShares;
    @ExcelProperty("流通股本")
    private BigDecimal circulatingShares;
    @ExcelProperty("上市价格")
    private BigDecimal issuePrice;
    @ExcelProperty("概念")
    private String concept;
    private List<String> conceptList;
    private Byte status;
    private Byte suspendedFlag;
    private Byte stFlag;
    private String logo;
    private String logoFilename;
}
