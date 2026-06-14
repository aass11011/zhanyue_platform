package com.zym.fastplatform.common.system.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.CommonServiceImpl;
import com.zym.fastplatform.common.system.convert.SysDictTypeConvertMapper;
import com.zym.fastplatform.common.system.dao.SysDictDataDao;
import com.zym.fastplatform.common.system.dao.SysDictTypeDao;
import com.zym.fastplatform.common.system.entity.SysDictType;
import com.zym.fastplatform.common.system.entity.dto.SysDictTypeDTO;
import com.zym.fastplatform.common.system.entity.vo.SysDictTypeVO;
import com.zym.fastplatform.common.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class SysDictTypeServiceImpl extends CommonServiceImpl<SysDictTypeDao,SysDictType, SysDictTypeConvertMapper, SysDictTypeDTO, SysDictTypeVO> implements SysDictTypeService{

    @Autowired
    private SysDictDataDao sysDictDataDao;
    @Override
    @Transactional
    public void delBatch(String[] ids) {
        dao.deleteAllById(Arrays.asList(ids));
        sysDictDataDao.deleteByTypeIdIn(ids);
    }

}
