package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockMarketField;
import com.zym.fastplatform.common.stock.entity.dto.StockMarketFieldDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMarketFieldVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMarketFieldConvertMapper extends BaseConvertMapper<StockMarketField, StockMarketFieldVO, StockMarketFieldDTO> {


}