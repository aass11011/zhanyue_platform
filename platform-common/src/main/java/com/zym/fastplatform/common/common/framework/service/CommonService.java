package com.zym.fastplatform.common.common.framework.service;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import org.springframework.data.domain.Page;

/**
 * 通用服务接口
 * @Notice 实体类id为String类型
 * @param <VO>
 * @param <DTO>
 */
public interface CommonService<VO,DTO extends BaseDTO> {
    VO findById(String id);
    void save(DTO entity);
    void delBatch(String[] ids);
    Page<VO> findAll(Integer page, Integer size, String sort, DTO condition);
}
