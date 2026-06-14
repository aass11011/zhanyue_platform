package com.zym.fastplatform.common.system.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDictDataVO {
    private String id;
    private String typeId;
    private String dictType;
    private Integer sortOrder;
    private String dictName;
    private String dictValue;
    private Integer status;
}
