package com.zym.fastplatform.common.book.convert;

import com.zym.fastplatform.common.book.entity.ReadNote;
import com.zym.fastplatform.common.book.entity.dto.ReadNoteDTO;
import com.zym.fastplatform.common.book.entity.vo.ReadNoteVO;
import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReadNoteConvertMapper extends BaseConvertMapper<ReadNote, ReadNoteVO, ReadNoteDTO> {


}