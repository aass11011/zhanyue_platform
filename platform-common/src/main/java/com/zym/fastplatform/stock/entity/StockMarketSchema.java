package com.zym.fastplatform.stock.entity;

import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stock_market_schema")
@Getter
@Setter
public class StockMarketSchema extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 模板名称
    */
    @Column
    private String name;

}