package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockConceptVO {
    private Long id;
    private String concept;
    private Integer levels;
    private Long parentId;
    private List<StockConceptVO> children;
}
