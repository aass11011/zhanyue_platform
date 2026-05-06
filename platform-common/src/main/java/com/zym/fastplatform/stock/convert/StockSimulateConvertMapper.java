package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockSimulate;
import com.zym.fastplatform.stock.entity.dto.StockSimulateDTO;
import com.zym.fastplatform.stock.entity.vo.StockSimulateVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockSimulateConvertMapper extends BaseConvertMapper<StockSimulate, StockSimulateVO, StockSimulateDTO> {


}