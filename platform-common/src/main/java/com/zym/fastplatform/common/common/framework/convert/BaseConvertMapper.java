package com.zym.fastplatform.common.common.framework.convert;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseConvertMapper<T,VO extends  Object,DTO extends BaseDTO> {
    VO toVO(T entity);

    default Page<VO> toVOPage(Page<T> entityPage){
        return entityPage.map(this::toVO);
    }

    T toEntity(DTO dto);

    List<VO> toVOList(Iterable<T> entities);

    List<T> toEntityList(Iterable<DTO> dtos);
}
