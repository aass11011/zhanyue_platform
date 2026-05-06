package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockMarketData;
import com.zym.fastplatform.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketDataVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMarketDataConvertMapper extends BaseConvertMapper<StockMarketData, StockMarketDataVO, StockMarketDataDTO> {


}