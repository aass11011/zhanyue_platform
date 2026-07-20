package com.zym.fastplatform.common.stock.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class StockMemorandumDTO extends BaseDTO {
    private Long id;
    private String stockCode;
    private String stockName;
    private Integer sortOrder;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rememberDate;
}
