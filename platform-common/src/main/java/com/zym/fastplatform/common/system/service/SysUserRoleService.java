package com.zym.fastplatform.common.system.service;

import com.zym.fastplatform.common.system.entity.vo.UserRolesVO;

import java.util.List;

public interface SysUserRoleService {


    void addUserRoleBatch(Long roleId, List<Long> userIds);

    void setUserRoles(UserRolesVO userRolesVO);
}