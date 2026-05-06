package com.zym.fastplatform.stock.entity;

import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "stock_spider_sse_funds")
@Getter
@Setter
public class StockSseFunds extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 日期
    */
    @Column
    private LocalDate statDate;

    /**
    * 基金代码
    */
    @Column
    private String secCode;

    /**
    * 基金名称
    */
    @Column
    private String secName;

    /**
    * 类型：单市/跨市
    */
    @Column
    private String etfType;

    /**
    * 总份额（万份）
    */
    @Column
    private BigDecimal totVol;


}