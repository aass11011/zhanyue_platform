package com.zym.fastplatform.common.system.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
    private Long id;
    private String name;
    private Long parentId;
    private Integer status;
    private String remark;
}
