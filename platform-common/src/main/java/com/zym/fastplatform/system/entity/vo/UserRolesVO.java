package com.zym.fastplatform.system.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRolesVO {
    private Long userId;
    private Long[] roleIds;
}
