package com.zym.fastplatform.common.system.dao;

import com.zym.fastplatform.common.system.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionDao extends JpaRepository<SysPermission,Long> {
    void deleteByMenuId(Long menuId);

    void deleteByMenuIdIn(List<Long> menuIds);
}
