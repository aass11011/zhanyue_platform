package com.zym.fastplatform.admin.system.service.impl;

import com.zym.fastplatform.system.dao.SysUserRoleDao;
import com.zym.fastplatform.system.entity.SysUserRole;
import com.zym.fastplatform.system.entity.vo.UserRolesVO;
import com.zym.fastplatform.admin.system.service.SysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleDao dao;

    @Override
    @Transactional
    public void addUserRoleBatch(Long roleId, List<Long> userIds) {
        userIds.forEach(user->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(user);
            dao.save(sysUserRole);
        });
    }

    @Override
    @Transactional
    public void setUserRoles(UserRolesVO userRolesVO) {
        dao.deleteByUserId(userRolesVO.getUserId());
        for (Long roleId : userRolesVO.getRoleIds()) {
            SysUserRole sysUserRole = SysUserRole.builder().roleId(roleId).userId(userRolesVO.getUserId()).build();
            dao.save(sysUserRole);
        }
    }
}