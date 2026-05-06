package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockOption;
import com.zym.fastplatform.stock.entity.dto.StockOptionDTO;
import com.zym.fastplatform.stock.entity.vo.StockOptionVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockOptionConvertMapper extends BaseConvertMapper<StockOption, StockOptionVO, StockOptionDTO> {


}