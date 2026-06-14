package com.zym.fastplatform.common.system.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.system.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleDao extends BaseDao<SysRole> {

    @Query("select r from SysRole r join SysUserRole ur on r.id = ur.roleId where ur.userId = :userId")
    Page<SysRole> selectRoleListByUserId(Long userId, PageRequest pageRequest);
}