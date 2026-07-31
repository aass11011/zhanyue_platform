package com.zym.fastplatform.common.stock.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zym.fastplatform.common.common.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.common.common.framework.annotation.Url;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @ExcelProperty("概念")
    @FuzzyQuery
    private String concept;
    @ExcelIgnore
    @Url
    private String logo;
    @ExcelIgnore
    private String logoFilename;
    @ExcelProperty("龙头板块")
    @FuzzyQuery
    private String leadingStockConcept;
}