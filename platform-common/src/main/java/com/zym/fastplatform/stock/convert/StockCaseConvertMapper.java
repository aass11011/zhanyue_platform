package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockCase;
import com.zym.fastplatform.stock.entity.dto.StockCaseDTO;
import com.zym.fastplatform.stock.entity.vo.StockCaseVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockCaseConvertMapper extends BaseConvertMapper<StockCase, StockCaseVO, StockCaseDTO> {


}