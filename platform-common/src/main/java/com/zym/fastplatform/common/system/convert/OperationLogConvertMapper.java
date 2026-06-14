package com.zym.fastplatform.common.system.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.system.entity.OperationLog;
import com.zym.fastplatform.common.system.entity.dto.OperationLogDTO;
import com.zym.fastplatform.common.system.entity.vo.OperationLogVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationLogConvertMapper extends BaseConvertMapper<OperationLog, OperationLogVO, OperationLogDTO> {
}
