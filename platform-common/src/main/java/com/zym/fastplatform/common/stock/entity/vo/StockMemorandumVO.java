package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class StockMemorandumVO {
    private Long id;
    private String stockCode;
    private String stockName;
    private Integer sortOrder;
    private String content;
    private LocalDate createdTime;
}
