package com.zym.fastplatform.book.convert;

import com.zym.fastplatform.book.entity.ReadNote;
import com.zym.fastplatform.book.entity.dto.ReadNoteDTO;
import com.zym.fastplatform.book.entity.vo.ReadNoteVO;
import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReadNoteConvertMapper extends BaseConvertMapper<ReadNote, ReadNoteVO, ReadNoteDTO> {


}