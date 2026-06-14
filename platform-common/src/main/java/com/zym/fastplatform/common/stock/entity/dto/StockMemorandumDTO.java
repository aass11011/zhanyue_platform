package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
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
