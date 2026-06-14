package com.zym.fastplatform.common.system.entity.vo;

import com.zym.fastplatform.common.system.entity.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
public class SysRoleVO {
    private Long id;
    private String name;
    private String roleKey;
    private Integer orderNum;
    private Integer status;
    private String remark;
    private Set<SysMenu> menus;
    private LocalDateTime createdTime;
}
