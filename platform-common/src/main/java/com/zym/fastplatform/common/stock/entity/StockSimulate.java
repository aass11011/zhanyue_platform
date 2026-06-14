package com.zym.fastplatform.common.stock.entity;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "stock_simulate")
@Getter
@Setter
public class StockSimulate extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 股票代码
    */
    @Column
    private String stockCode;

    /**
    * 股票名称
    */
    @Column
    private String stockName;

    /**
    * 
    */
    @Column
    private LocalDate startDate;

    /**
    * 
    */
    @Column
    private LocalDate endDate;


}