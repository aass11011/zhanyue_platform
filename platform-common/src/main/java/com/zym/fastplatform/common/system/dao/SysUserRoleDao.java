package com.zym.fastplatform.common.system.dao;

import com.zym.fastplatform.common.system.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SysUserRoleDao extends JpaRepository<SysUserRole,Long> {


    List<SysUserRole> findByRoleId(Long roleId);

    void deleteByUserId(Long userId);

    void deleteAllByUserIdIn(Collection<Long> userIds);
}