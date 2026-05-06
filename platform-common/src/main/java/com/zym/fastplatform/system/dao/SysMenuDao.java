package com.zym.fastplatform.system.dao;

import com.zym.fastplatform.framework.dao.BaseDao;
import com.zym.fastplatform.system.entity.SysMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuDao extends BaseDao<SysMenu> {

    
    List<SysMenu> findByParentId(Long parentId);

    List<SysMenu> findByParentIdIsNull();

    List<SysMenu> findByHideFlag(Integer hiddenFlag);
    @Query("select sp from SysMenu sp join SysRoleMenu srp on sp.id = srp.menuId " +
            "join SysUserRole  sur on srp.roleId = sur.roleId where sur.userId = :userId")
    List<SysMenu> findByUserId(@Param("userId") Long userId);
}