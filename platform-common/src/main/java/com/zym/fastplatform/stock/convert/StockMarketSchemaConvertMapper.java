package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockMarketSchema;
import com.zym.fastplatform.stock.entity.dto.StockMarketSchemaDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketSchemaVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMarketSchemaConvertMapper extends BaseConvertMapper<StockMarketSchema, StockMarketSchemaVO, StockMarketSchemaDTO> {


}