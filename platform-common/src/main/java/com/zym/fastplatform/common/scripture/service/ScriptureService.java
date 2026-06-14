package com.zym.fastplatform.common.scripture.service;

import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.scripture.entity.Scripture;
import com.zym.fastplatform.common.scripture.entity.dto.ScriptureDTO;
import com.zym.fastplatform.common.scripture.entity.vo.ScriptureVO;

import java.util.List;

public interface ScriptureService extends BaseService<Scripture, ScriptureVO, ScriptureDTO> {


    List<ScriptureVO> listTitle(Integer page, Integer size, String sort, ScriptureDTO condition);
}