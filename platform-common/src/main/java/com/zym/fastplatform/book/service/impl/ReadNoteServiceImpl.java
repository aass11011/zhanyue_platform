package com.zym.fastplatform.book.service.impl;

import com.zym.fastplatform.book.convert.ReadNoteConvertMapper;
import com.zym.fastplatform.book.dao.ReadNoteDao;
import com.zym.fastplatform.book.entity.ReadNote;
import com.zym.fastplatform.book.entity.dto.ReadNoteDTO;
import com.zym.fastplatform.book.entity.vo.ReadNoteVO;
import com.zym.fastplatform.book.service.ReadNoteService;
import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ReadNoteServiceImpl extends BaseServiceImpl<ReadNoteDao, ReadNote, ReadNoteConvertMapper, ReadNoteDTO, ReadNoteVO> implements ReadNoteService {



}