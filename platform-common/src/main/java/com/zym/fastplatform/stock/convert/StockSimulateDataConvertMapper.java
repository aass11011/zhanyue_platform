package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockSimulateData;
import com.zym.fastplatform.stock.entity.dto.StockSimulateDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockSimulateDataVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockSimulateDataConvertMapper extends BaseConvertMapper<StockSimulateData, StockSimulateDataVO, StockSimulateDataDTO> {


}