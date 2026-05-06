package com.zym.fastplatform.stock.entity.vo;

import com.zym.fastplatform.stock.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class StockTradeDetailVO {
    private Long id;

    private String stockCode;

    private String stockName;

    private LocalDate tradeDate;

    private LocalTime tradeTime;

    private BigDecimal price;

    private Integer volumn;

    private Direction direction;

    private Integer orders;
}
