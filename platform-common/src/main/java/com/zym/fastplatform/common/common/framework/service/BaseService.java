package com.zym.fastplatform.common.common.framework.service;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService <T extends BaseEntity,VO,DTO extends BaseDTO> {

    T findById(Long id);

    VO findVOById(Long id);

    T save(T entity);

    void deleteById(Long id);

    Page<VO> find(String sort, DTO condition);

    void delBatch(Long[] ids);

    void save(DTO dto);

    List<VO> findAll(String sort,DTO condition);
}
