package com.zym.fastplatform.system.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.system.convert.OperationLogConvertMapper;
import com.zym.fastplatform.system.dao.OperationLogDao;
import com.zym.fastplatform.system.entity.OperationLog;
import com.zym.fastplatform.system.entity.dto.OperationLogDTO;
import com.zym.fastplatform.system.entity.vo.OperationLogVO;
import com.zym.fastplatform.system.service.OperationLogService;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLogDao, OperationLog, OperationLogConvertMapper, OperationLogDTO, OperationLogVO> implements OperationLogService {
}
