package com.zym.fastplatform.stock.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
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