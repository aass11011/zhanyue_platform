package com.zym.fastplatform.common.scripture.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.scripture.entity.Scripture;
import com.zym.fastplatform.common.scripture.entity.dto.ScriptureDTO;
import com.zym.fastplatform.common.scripture.entity.vo.ScriptureVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScriptureConvertMapper extends BaseConvertMapper<Scripture, ScriptureVO, ScriptureDTO> {


}