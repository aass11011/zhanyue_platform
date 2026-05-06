package com.zym.fastplatform.stock.entity.vo;

import com.zym.fastplatform.stock.enums.Direction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class StockTradeDayAnalysisVO {
    private String stockCode;
    private String stockName;
    private Integer bigBuyCount;
    private Integer bigSellCount;
    private Integer smallBuyCount;
    private Integer smallSellCount;
    private BigDecimal bigBuyAccount;
    private BigDecimal bigSellAccount;
    private BigDecimal smallBuyAccount;
    private BigDecimal smallSellAccount;
    private BigDecimal bigSum;
    private BigDecimal smallSum;
    private Integer[] bigBuy;
    private LocalTime[] bigBuyTime;
    private BigDecimal[] bigBuyPrice;
    private Integer[] bigSell;
    private LocalTime[] bigSellTime;
    private BigDecimal[] bigSellPrice;
    private Integer[] bigVolume;
    private LocalTime[] bigTime;
    private BigDecimal[] bigPrice;
    private Direction[] bigDirection;
    private Integer[] smallBuy;
    private LocalTime[] smallBuyTime;
    private BigDecimal[] smallBuyPrice;
    private Integer[] smallSell;
    private LocalTime[] smallSellTime;
    private BigDecimal[] smallSellPrice;
    private Integer[] smallVolume;
    private LocalTime[] smallTime;
    private BigDecimal[] smallPrice;
    private Direction[] smallDirection;
    private LocalDate tradeDate;
}
