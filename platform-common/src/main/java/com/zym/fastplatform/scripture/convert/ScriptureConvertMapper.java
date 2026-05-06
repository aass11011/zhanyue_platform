package com.zym.fastplatform.scripture.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.scripture.entity.Scripture;
import com.zym.fastplatform.scripture.entity.dto.ScriptureDTO;
import com.zym.fastplatform.scripture.entity.vo.ScriptureVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScriptureConvertMapper extends BaseConvertMapper<Scripture, ScriptureVO, ScriptureDTO> {


}