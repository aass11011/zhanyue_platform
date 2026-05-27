package com.zym.fastplatform.framework.service;

import com.zym.fastplatform.framework.entity.BaseDTO;
import com.zym.fastplatform.framework.entity.NoStatusBaseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoStatusBaseService<T extends NoStatusBaseEntity,VO,DTO extends BaseDTO> {

    T findById(Long id);

    VO findVOById(Long id);

    T save(T entity);

    void deleteById(Long id);

    Page<VO> findAll(Integer page, Integer size, String sort, DTO condition);

    void delBatch(Long[] ids);

    void save(DTO dto);

    List<VO> findAll(String sort,DTO condition);
}
