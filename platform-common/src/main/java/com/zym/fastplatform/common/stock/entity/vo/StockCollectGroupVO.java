package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCollectGroupVO{

    private Long id;

    private Long userId;

    private String groupName;

    private Boolean isDefault;

}