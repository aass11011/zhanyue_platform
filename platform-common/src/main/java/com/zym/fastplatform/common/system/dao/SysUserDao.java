package com.zym.fastplatform.common.system.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.system.entity.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysUserDao extends BaseDao<SysUser> {

    SysUser findByUsername(String username);
    @Transactional
    @Query("SELECT u FROM SysUser u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.menus WHERE u.username = :username")
    Optional<SysUser> findByUsernameWithRolesAndPermissions(@Param("username")String username);

    List<SysUser> findByDeptIdIn(Long[] ids);
}
