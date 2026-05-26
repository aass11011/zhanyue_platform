package com.zym.fastplatform.admin.system.service.impl;

import com.zym.fastplatform.admin.framework.service.impl.CommonServiceImpl;
import com.zym.fastplatform.system.convert.SysDictTypeConvertMapper;
import com.zym.fastplatform.system.dao.SysDictDataDao;
import com.zym.fastplatform.system.dao.SysDictTypeDao;
import com.zym.fastplatform.system.entity.SysDictType;
import com.zym.fastplatform.system.entity.dto.SysDictTypeDTO;
import com.zym.fastplatform.system.entity.vo.SysDictTypeVO;
import com.zym.fastplatform.admin.system.service.SysDictTypeService;
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
