package com.zym.fastplatform.common.system.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.system.entity.SysRole;
import com.zym.fastplatform.common.system.entity.dto.SysRoleDTO;
import com.zym.fastplatform.common.system.entity.vo.SysRoleVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysRoleConvertMapper extends BaseConvertMapper<SysRole, SysRoleVO, SysRoleDTO> {
}
