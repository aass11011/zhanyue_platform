package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;


@Getter
@Setter
@ToString
public class TradeDensityVO {
    private LocalTime timeInterval; // 时间区间
    private String timeStr;        // 时间字符串（如：09:30）
    private BigDecimal openPrice;  // 开盘价
    private BigDecimal closePrice; // 收盘价
    private BigDecimal highPrice;  // 最高价
    private BigDecimal lowPrice;   // 最低价
    private Integer totalVolume;   // 区间总成交量
    private Integer totalTradeCount; // 区间总成交笔数
    private BigDecimal tradeDensity; // 交易密度
    private BigDecimal priceChange; // 价格变化
    private BigDecimal priceChangePct; // 价格变化率（%）
}
