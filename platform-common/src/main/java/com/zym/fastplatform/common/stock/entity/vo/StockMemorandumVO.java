package com.zym.fastplatform.common.stock.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rememberDate;
}
