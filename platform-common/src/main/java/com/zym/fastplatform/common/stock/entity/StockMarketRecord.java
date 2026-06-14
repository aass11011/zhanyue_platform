package com.zym.fastplatform.common.stock.entity;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stock_market_record")
@Getter
@Setter
public class StockMarketRecord extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private Integer schemaId;
    @Transient
    private List<StockMarketData> list;
   }
