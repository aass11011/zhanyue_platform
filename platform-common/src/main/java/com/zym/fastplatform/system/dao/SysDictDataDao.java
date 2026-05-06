package com.zym.fastplatform.system.dao;

import com.zym.fastplatform.framework.dao.CommonDao;
import com.zym.fastplatform.system.entity.SysDictData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictDataDao extends CommonDao<SysDictData> {
    void deleteByTypeId(String id);

    void deleteByTypeIdIn(String[] ids);

    List<SysDictData> findByTypeId(String typeId);

    SysDictData findByDictNameAndTypeId(String concept, String uuid);
}
