package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMemorandumDTO extends BaseDTO {
    private Long id;
    private String stockCode;
    private String stockName;
    private Integer sortOrder;
    private String content;
}
