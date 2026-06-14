package com.zym.fastplatform.common.system.service;

import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.system.entity.SysRole;
import com.zym.fastplatform.common.system.entity.dto.SysRoleDTO;
import com.zym.fastplatform.common.system.entity.vo.SysRoleVO;
import org.springframework.data.domain.Page;

public interface SysRoleService extends BaseService<SysRole, SysRoleVO,SysRoleDTO> {

    void save(SysRoleDTO entity);

    Page<SysRole> selectByUserId(Integer pageNum,Integer pageSize,Long userId);
}