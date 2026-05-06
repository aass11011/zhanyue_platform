package com.zym.fastplatform.system.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.system.entity.OperationLog;
import com.zym.fastplatform.system.entity.dto.OperationLogDTO;
import com.zym.fastplatform.system.entity.vo.OperationLogVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationLogConvertMapper extends BaseConvertMapper<OperationLog, OperationLogVO, OperationLogDTO> {
}
