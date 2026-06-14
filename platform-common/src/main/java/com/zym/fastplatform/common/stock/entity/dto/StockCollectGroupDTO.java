package com.zym.fastplatform.common.stock.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCollectGroupDTO extends BaseDTO {

    private Long id;

    private Long userId;


    private String groupName;

    private Boolean isDefault;

}