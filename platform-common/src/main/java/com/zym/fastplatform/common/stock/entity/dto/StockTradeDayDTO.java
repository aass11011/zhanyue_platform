package com.zym.fastplatform.common.stock.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StockTradeDayDTO {
    private String stockCode;
    private LocalDate tradeDate;
}
