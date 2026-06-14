package com.zym.fastplatform.common.book.convert;

import com.zym.fastplatform.common.book.entity.ReadBook;
import com.zym.fastplatform.common.book.entity.dto.ReadBookDTO;
import com.zym.fastplatform.common.book.entity.vo.ReadBookVO;
import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReadBookConvertMapper extends BaseConvertMapper<ReadBook, ReadBookVO, ReadBookDTO> {


}