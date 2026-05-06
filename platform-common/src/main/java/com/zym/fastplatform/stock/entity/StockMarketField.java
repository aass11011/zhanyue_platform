package com.zym.fastplatform.stock.entity;

import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "stock_market_field")
@Getter
@Setter
public class StockMarketField extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 
    */
    @Column
    private Long schemaId;

    /**
    * 字段名称
    */
    @Column
    private String fieldName;

    /**
    * 字段类型id
    */
    @Column
    private String fieldTypeId;

    /**
    * 
    */
    @Column
    private Integer sortOrder;

    /**
    * 是否必填
    */
    @Column
    private Boolean isRequired;

    /**
    * 字段选项
    */
    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String fieldOptions;


}