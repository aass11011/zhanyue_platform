package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import com.zym.fastplatform.common.stock.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class StockTradeDetailDTO extends BaseDTO {
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
