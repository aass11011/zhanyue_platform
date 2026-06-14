package com.zym.fastplatform.common.system.service.impl;

import com.zym.fastplatform.common.common.framework.exception.ZException;
import com.zym.fastplatform.common.common.framework.service.impl.CommonServiceImpl;
import com.zym.fastplatform.common.system.convert.SysDictDataConvertMapper;
import com.zym.fastplatform.common.system.dao.SysDictDataDao;
import com.zym.fastplatform.common.system.entity.SysDictData;
import com.zym.fastplatform.common.system.entity.dto.SysDictDataDTO;
import com.zym.fastplatform.common.system.entity.vo.SysDictDataVO;
import com.zym.fastplatform.common.system.service.SysDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictDataServiceImpl extends CommonServiceImpl<SysDictDataDao,SysDictData, SysDictDataConvertMapper, SysDictDataDTO, SysDictDataVO> implements SysDictDataService {

    @Override
    public List<SysDictDataVO> listByType(String typeId) {
        return convertMapper.toVO(dao.findByTypeId(typeId));
    }

    @Override
    public void save(SysDictDataDTO entity) {
        if(entity.getId()==null){//新增
            SysDictData sysDictData = dao.findByDictNameAndTypeId(entity.getDictName(),entity.getTypeId());
            if(sysDictData != null){
                throw new ZException("字典数据已存在");
            }else {
                super.save(entity);
            }
        }else {
            super.save(entity);
        }
    }
}
