package com.zym.fastplatform.system.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.system.entity.SysDictType;
import com.zym.fastplatform.system.entity.dto.SysDictTypeDTO;
import com.zym.fastplatform.system.entity.vo.SysDictTypeVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysDictTypeConvertMapper extends BaseConvertMapper<SysDictType, SysDictTypeVO, SysDictTypeDTO> {
}
