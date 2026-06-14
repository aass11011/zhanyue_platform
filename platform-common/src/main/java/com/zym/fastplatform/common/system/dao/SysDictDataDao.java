package com.zym.fastplatform.common.system.dao;

import com.zym.fastplatform.common.common.framework.dao.CommonDao;
import com.zym.fastplatform.common.system.entity.SysDictData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictDataDao extends CommonDao<SysDictData> {
    void deleteByTypeId(String id);

    void deleteByTypeIdIn(String[] ids);

    List<SysDictData> findByTypeId(String typeId);

    SysDictData findByDictNameAndTypeId(String concept, String uuid);
}
