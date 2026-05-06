package com.zym.fastplatform.admin.scripture.controller;

import com.zym.fastplatform.framework.controller.BaseController;
import com.zym.fastplatform.scripture.entity.Scripture;
import com.zym.fastplatform.scripture.entity.dto.ScriptureDTO;
import com.zym.fastplatform.scripture.entity.vo.ScriptureVO;
import com.zym.fastplatform.scripture.service.ScriptureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scripture")
@Slf4j
public class ScriptureController extends BaseController<ScriptureService, Scripture, ScriptureDTO, ScriptureVO> {


}