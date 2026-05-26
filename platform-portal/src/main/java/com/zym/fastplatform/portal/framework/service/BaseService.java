package com.zym.fastplatform.portal.framework.service;

import com.zym.fastplatform.framework.entity.BaseDTO;
import com.zym.fastplatform.framework.entity.BaseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService <T extends BaseEntity,VO,DTO extends BaseDTO> {
    T findById(Long id);

    VO findVOById(Long id);

    Page<VO> findAll(Integer page,Integer size,String sort,DTO condition);

    List<VO> findAll(String sort,DTO condition);
}
