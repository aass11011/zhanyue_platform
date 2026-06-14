package com.zym.fastplatform.common.stock.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import com.zym.fastplatform.common.stock.convert.LocalTimeConverter;
import com.zym.fastplatform.common.stock.enums.Direction;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockTradeDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ExcelIgnore
    private Long id;

    @Size(max = 255)
    @Column(name = "stock_code")
    @ExcelIgnore
    private String stockCode;

    @Size(max = 255)
    @Column(name = "stock_name")
    @ExcelIgnore
    private String stockName;

    @Column(name = "trade_date")
    @ExcelIgnore
    private LocalDate tradeDate;

    @Column(name = "trade_time")
    @ExcelProperty(value = "时间",index = 0,converter = LocalTimeConverter.class)
    private LocalTime tradeTime;

    @Column(name = "price", precision = 10, scale = 2)
    @ExcelProperty(value = "价格", index = 1)
    private BigDecimal price;

    @Column(name = "volumn")
    @ExcelProperty(index = 2)
    private Integer volumn;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction")
    @ExcelProperty(index = 4)
    private Direction direction;

    @Column(name = "orders")
    @ExcelProperty(index = 3)
    private Integer orders;

}