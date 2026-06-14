package com.zym.fastplatform.common.system.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.system.entity.SysDictData;
import com.zym.fastplatform.common.system.entity.dto.SysDictDataDTO;
import com.zym.fastplatform.common.system.entity.vo.SysDictDataVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysDictDataConvertMapper extends BaseConvertMapper<SysDictData, SysDictDataVO, SysDictDataDTO> {
    List<SysDictDataVO> toVO(List<SysDictData> list);
}
