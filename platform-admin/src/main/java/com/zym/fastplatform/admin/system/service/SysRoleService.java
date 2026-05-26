package com.zym.fastplatform.admin.system.service;

import com.zym.fastplatform.admin.framework.service.BaseService;
import com.zym.fastplatform.system.entity.SysRole;
import com.zym.fastplatform.system.entity.dto.SysRoleDTO;
import com.zym.fastplatform.system.entity.vo.SysRoleVO;
import org.springframework.data.domain.Page;

public interface SysRoleService extends BaseService<SysRole, SysRoleVO,SysRoleDTO> {

    void save(SysRoleDTO entity);

    Page<SysRole> selectByUserId(Integer pageNum,Integer pageSize,Long userId);
}