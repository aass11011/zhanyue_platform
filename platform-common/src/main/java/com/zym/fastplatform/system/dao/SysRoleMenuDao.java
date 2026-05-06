package com.zym.fastplatform.system.dao;

import com.zym.fastplatform.system.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMenuDao extends JpaRepository<SysRoleMenu,Long> {


    void deleteByRoleId(Long roleId);

    void deleteByRoleIdIn(Long[] ids);
}
