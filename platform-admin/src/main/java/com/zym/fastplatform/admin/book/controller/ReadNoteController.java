package com.zym.fastplatform.admin.book.controller;

import com.zym.fastplatform.framework.controller.BaseController;
import com.zym.fastplatform.book.entity.ReadNote;
import com.zym.fastplatform.book.entity.dto.ReadNoteDTO;
import com.zym.fastplatform.book.entity.vo.ReadNoteVO;
import com.zym.fastplatform.book.service.ReadNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read/note")
@Slf4j
public class ReadNoteController extends BaseController<ReadNoteService, ReadNote, ReadNoteDTO, ReadNoteVO> {


}