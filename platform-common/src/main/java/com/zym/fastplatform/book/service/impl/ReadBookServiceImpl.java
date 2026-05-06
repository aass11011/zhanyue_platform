package com.zym.fastplatform.book.service.impl;

import com.zym.fastplatform.book.convert.ReadBookConvertMapper;
import com.zym.fastplatform.book.dao.ReadBookDao;
import com.zym.fastplatform.book.entity.ReadBook;
import com.zym.fastplatform.book.entity.dto.ReadBookDTO;
import com.zym.fastplatform.book.entity.vo.ReadBookVO;
import com.zym.fastplatform.book.service.ReadBookService;
import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ReadBookServiceImpl extends BaseServiceImpl<ReadBookDao, ReadBook, ReadBookConvertMapper, ReadBookDTO, ReadBookVO> implements ReadBookService {



}