package com.zym.fastplatform.common.system.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.system.entity.SysUser;
import com.zym.fastplatform.common.system.entity.dto.SysUserDTO;
import com.zym.fastplatform.common.system.entity.vo.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SysUserConvertMapper extends BaseConvertMapper<SysUser, SysUserVO, SysUserDTO> {
    @Override
    @Mapping(target = "roles", ignore = true )
    SysUserVO toVO(SysUser user);

    @Override
    @Mapping(target = "roles", ignore = true )
    SysUser toEntity(SysUserDTO dto);
}
