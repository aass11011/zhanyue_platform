package com.zym.fastplatform.admin.book.controller;

import com.zym.fastplatform.framework.controller.BaseController;
import com.zym.fastplatform.book.entity.ReadBook;
import com.zym.fastplatform.book.entity.dto.ReadBookDTO;
import com.zym.fastplatform.book.entity.vo.ReadBookVO;
import com.zym.fastplatform.book.service.ReadBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read/book")
@Slf4j
public class ReadBookController extends BaseController<ReadBookService, ReadBook, ReadBookDTO, ReadBookVO> {


}