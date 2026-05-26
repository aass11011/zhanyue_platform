package com.zym.fastplatform.admin.system.service;

import com.zym.fastplatform.admin.framework.service.CommonService;
import com.zym.fastplatform.system.entity.dto.SysDictDataDTO;
import com.zym.fastplatform.system.entity.vo.SysDictDataVO;

import java.util.List;

public interface SysDictDataService extends CommonService<SysDictDataVO, SysDictDataDTO> {
    List<SysDictDataVO> listByType(String typeId);
}
