package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockSimulateData;
import com.zym.fastplatform.common.stock.entity.dto.StockSimulateDataDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockSimulateDataVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockSimulateDataConvertMapper extends BaseConvertMapper<StockSimulateData, StockSimulateDataVO, StockSimulateDataDTO> {


}