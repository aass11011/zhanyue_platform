package com.zym.fastplatform.stock.entity;

import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "stock_trade_day")
@AttributeOverrides({
        @AttributeOverride(name = "createdBy", column = @Column(name = "created_by")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "updated_by"))
})
public class StockTradeDay extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "trade_date")
    private LocalDate tradeDate;

    @Column(name = "big_buy_count")
    private Integer bigBuyCount;

    @Column(name = "big_sell_count")
    private Integer bigSellCount;

    @Column(name = "big_buy_account", precision = 10, scale = 2)
    private BigDecimal bigBuyAccount;

    @Column(name = "big_sell_account", precision = 10, scale = 2)
    private BigDecimal bigSellAccount;

    @Column(name = "big_sum", precision = 10, scale = 2)
    private BigDecimal bigSum;

    @Column(name = "small_buy_count")
    private Integer smallBuyCount;

    @Column(name = "small_sell_count")
    private Integer smallSellCount;

    @Column(name = "small_buy_account", precision = 10, scale = 2)
    private BigDecimal smallBuyAccount;

    @Column(name = "small_sell_account", precision = 10, scale = 2)
    private BigDecimal smallSellAccount;

    @Column(name = "small_sum", precision = 10, scale = 2)
    private BigDecimal smallSum;

    @Column(name = "threshold")
    private Integer threshold;
}