package com.zym.fastplatform.common.system.service.impl;

import com.zym.fastplatform.common.system.convert.OperationLogConvertMapper;
import com.zym.fastplatform.common.system.dao.OperationLogDao;
import com.zym.fastplatform.common.system.entity.OperationLog;
import com.zym.fastplatform.common.system.entity.dto.OperationLogDTO;
import com.zym.fastplatform.common.system.entity.vo.OperationLogVO;
import com.zym.fastplatform.common.system.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    private OperationLogDao dao;

    @Autowired
    private OperationLogConvertMapper convertMapper;
    @Override
    public OperationLogVO findById(Long id) {
        return convertMapper.toVO(dao.findById(id).orElse(null));
    }

    @Override
    public Page<OperationLogVO> findAll(Integer page, Integer size, String sort, OperationLogDTO condition) {
        return convertMapper.toVOPage(dao.findAll(Example.of(convertMapper.toEntity(condition)), PageRequest.of(page, size)));
    }

    @Override
    public void save(OperationLogDTO dto) {
        dao.save(convertMapper.toEntity(dto));
    }

    @Override
    public void save(OperationLog operationLog) {
        dao.save(operationLog);
    }
}
