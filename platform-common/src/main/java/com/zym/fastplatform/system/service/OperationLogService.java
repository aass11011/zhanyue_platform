package com.zym.fastplatform.system.service;

import com.zym.fastplatform.system.entity.OperationLog;
import com.zym.fastplatform.system.entity.dto.OperationLogDTO;
import com.zym.fastplatform.system.entity.vo.OperationLogVO;
import org.springframework.data.domain.Page;

public interface OperationLogService {
    OperationLogVO findById(Long id);

    Page<OperationLogVO> findAll(Integer page, Integer size, String sort, OperationLogDTO condition);

    void save(OperationLogDTO dto);

    void save(OperationLog operationLog);
}
