package com.zym.fastplatform.stock.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockTradeEchartVO {
    private LocalTime date;
    private Integer volume;
}
