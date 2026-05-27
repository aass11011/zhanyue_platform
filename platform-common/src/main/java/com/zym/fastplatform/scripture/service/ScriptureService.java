package com.zym.fastplatform.scripture.service;

import com.zym.fastplatform.framework.service.BaseService;
import com.zym.fastplatform.scripture.entity.Scripture;
import com.zym.fastplatform.scripture.entity.dto.ScriptureDTO;
import com.zym.fastplatform.scripture.entity.vo.ScriptureVO;

import java.util.List;

public interface ScriptureService extends BaseService<Scripture, ScriptureVO, ScriptureDTO> {


    List<ScriptureVO> listTitle(Integer page, Integer size, String sort, ScriptureDTO condition);
}