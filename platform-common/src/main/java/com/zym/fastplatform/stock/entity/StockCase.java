package com.zym.fastplatform.stock.entity;

import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "stock_case")
@Getter
@Setter
public class StockCase extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 标题
    */
    @Column
    private String title;

    /**
    * 内容
    */
    @Column
    private String content;

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
    * 案例日期
    */
    @Column
    private LocalDate caseDate;

    /**
    * 案例分类
    */
    @Column
    private String category;

    /**
    * 标签
    */
    @Column
    private String tags;
}