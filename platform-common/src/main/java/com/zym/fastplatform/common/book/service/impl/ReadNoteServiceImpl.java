package com.zym.fastplatform.common.book.service.impl;

import com.zym.fastplatform.common.book.convert.ReadNoteConvertMapper;
import com.zym.fastplatform.common.book.dao.ReadNoteDao;
import com.zym.fastplatform.common.book.entity.ReadNote;
import com.zym.fastplatform.common.book.entity.dto.ReadNoteDTO;
import com.zym.fastplatform.common.book.entity.vo.ReadNoteVO;
import com.zym.fastplatform.common.book.service.ReadNoteService;
import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ReadNoteServiceImpl extends BaseServiceImpl<ReadNoteDao, ReadNote, ReadNoteConvertMapper, ReadNoteDTO, ReadNoteVO> implements ReadNoteService {



}