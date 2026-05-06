package com.zym.fastplatform.system.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.system.entity.SysRole;
import com.zym.fastplatform.system.entity.dto.SysRoleDTO;
import com.zym.fastplatform.system.entity.vo.SysRoleVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysRoleConvertMapper extends BaseConvertMapper<SysRole, SysRoleVO, SysRoleDTO> {
}
