package com.zym.fastplatform.common.stock.entity.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import com.zym.fastplatform.common.stock.entity.StockConceptRel;
import lombok.Getter;
import lombok.Setter;

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
    private List<StockConceptRel> stockConceptList;
    private Byte status;
    private String logo;
    private String logoFilename;
}
