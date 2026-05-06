package com.zym.fastplatform.stock.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.zym.fastplatform.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.framework.annotation.Url;
import com.zym.fastplatform.framework.entity.BaseEntity;
import com.zym.fastplatform.stock.convert.ByteEnumConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "stock_basic")
public class StockBasic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelIgnore
    private Long id;
    @ExcelProperty("股票代码")
    @FuzzyQuery
    private String stockCode;
    @ExcelProperty("股票简称")
    @FuzzyQuery
    private String stockShortName;
    @ExcelProperty("股票全称")
    @FuzzyQuery
    private String stockFullName;
    @ExcelProperty("交易所")
    private String exchange;
    @ExcelProperty("市场类型")
    private String marketType;
    @ExcelProperty("行业")
    @FuzzyQuery
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
    @FuzzyQuery
    private String concept;
    @ExcelProperty(value = "状态",converter = ByteEnumConverter.class)
    private Byte status;
    @Column(name="is_suspended")
    @ExcelIgnore
    private Byte suspendedFlag;
    @Column(name="is_st")
    @ExcelIgnore
    private Byte stFlag;
    @ExcelIgnore
    @Url
    private String logo;
    @ExcelIgnore
    private String logoFilename;
}