package com.zym.fastplatform.common.system.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysPermissionDTO {
    private String label;
    private String value;
    private Long menuId;
}
