package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockMarketData;
import com.zym.fastplatform.common.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMarketDataVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMarketDataConvertMapper extends BaseConvertMapper<StockMarketData, StockMarketDataVO, StockMarketDataDTO> {


}