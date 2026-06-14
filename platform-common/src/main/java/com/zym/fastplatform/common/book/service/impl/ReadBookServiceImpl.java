package com.zym.fastplatform.common.book.service.impl;

import com.zym.fastplatform.common.book.convert.ReadBookConvertMapper;
import com.zym.fastplatform.common.book.dao.ReadBookDao;
import com.zym.fastplatform.common.book.entity.ReadBook;
import com.zym.fastplatform.common.book.entity.dto.ReadBookDTO;
import com.zym.fastplatform.common.book.entity.vo.ReadBookVO;
import com.zym.fastplatform.common.book.service.ReadBookService;
import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ReadBookServiceImpl extends BaseServiceImpl<ReadBookDao, ReadBook, ReadBookConvertMapper, ReadBookDTO, ReadBookVO> implements ReadBookService {



}