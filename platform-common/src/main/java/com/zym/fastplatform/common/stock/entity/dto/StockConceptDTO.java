package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockConceptDTO extends BaseDTO {
    private Long id;
    private String concept;
    private Integer levels;
    private Long parentId;
}
