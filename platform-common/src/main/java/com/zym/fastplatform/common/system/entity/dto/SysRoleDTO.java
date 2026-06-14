package com.zym.fastplatform.common.system.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRoleDTO extends BaseDTO {
    private Long id;
    private String name;
    private String roleKey;
    private Integer orderNum;
    private Integer status;
    private Long[] menuIds;
}
