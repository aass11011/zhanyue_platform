package com.zym.fastplatform.system.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
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
