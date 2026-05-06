package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCollectItemDTO extends BaseDTO {
    private Long id;
    private Long groupId;
    private String stockCode;
}