package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockSimulate;
import com.zym.fastplatform.common.stock.entity.dto.StockSimulateDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockSimulateVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockSimulateConvertMapper extends BaseConvertMapper<StockSimulate, StockSimulateVO, StockSimulateDTO> {


}