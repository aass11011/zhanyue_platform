package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockCase;
import com.zym.fastplatform.common.stock.entity.dto.StockCaseDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCaseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockCaseConvertMapper extends BaseConvertMapper<StockCase, StockCaseVO, StockCaseDTO> {


}