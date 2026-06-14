package com.zym.fastplatform.common.scripture.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.scripture.entity.Scripture;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptureDao extends BaseDao<Scripture> {

    @Query("SELECT s.id, s.title FROM Scripture s")
    List<Scripture> findIdAndTitle(Specification<Scripture> specification, PageRequest pageRequest);
}