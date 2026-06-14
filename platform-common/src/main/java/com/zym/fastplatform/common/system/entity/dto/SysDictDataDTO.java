package com.zym.fastplatform.common.system.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDictDataDTO extends BaseDTO {
    private String id;
    private String typeId;
    private String dictType;
    private Integer sortOrder;
    private String dictName;
    private String dictValue;
    private Integer status;
}
