package com.zym.fastplatform.common.system.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDictTypeDTO extends BaseDTO {
    private String id;
    private String dictType;
    private Integer sortOrder;
    private String description;
    private Integer status;
}
