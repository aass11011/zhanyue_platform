package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockMarketSchema;
import com.zym.fastplatform.common.stock.entity.dto.StockMarketSchemaDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMarketSchemaVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMarketSchemaConvertMapper extends BaseConvertMapper<StockMarketSchema, StockMarketSchemaVO, StockMarketSchemaDTO> {


}