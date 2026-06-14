package com.zym.fastplatform.common.system.service;

import com.zym.fastplatform.common.common.framework.service.CommonService;
import com.zym.fastplatform.common.system.entity.dto.SysDictDataDTO;
import com.zym.fastplatform.common.system.entity.vo.SysDictDataVO;

import java.util.List;

public interface SysDictDataService extends CommonService<SysDictDataVO, SysDictDataDTO> {
    List<SysDictDataVO> listByType(String typeId);
}
