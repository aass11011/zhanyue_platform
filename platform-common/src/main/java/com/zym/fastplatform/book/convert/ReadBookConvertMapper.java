package com.zym.fastplatform.book.convert;

import com.zym.fastplatform.book.entity.ReadBook;
import com.zym.fastplatform.book.entity.dto.ReadBookDTO;
import com.zym.fastplatform.book.entity.vo.ReadBookVO;
import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReadBookConvertMapper extends BaseConvertMapper<ReadBook, ReadBookVO, ReadBookDTO> {


}