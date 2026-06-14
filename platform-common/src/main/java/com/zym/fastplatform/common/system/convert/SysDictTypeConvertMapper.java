package com.zym.fastplatform.common.system.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.system.entity.SysDictType;
import com.zym.fastplatform.common.system.entity.dto.SysDictTypeDTO;
import com.zym.fastplatform.common.system.entity.vo.SysDictTypeVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysDictTypeConvertMapper extends BaseConvertMapper<SysDictType, SysDictTypeVO, SysDictTypeDTO> {
}
