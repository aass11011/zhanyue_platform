package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockMarketField;
import com.zym.fastplatform.stock.entity.dto.StockMarketFieldDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketFieldVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMarketFieldConvertMapper extends BaseConvertMapper<StockMarketField, StockMarketFieldVO, StockMarketFieldDTO> {


}