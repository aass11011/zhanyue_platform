package com.zym.fastplatform.common.system.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDictTypeVO {
    private String id;
    private String dictType;
    private Integer sortOrder;
    private String description;
    private Integer status;
}
