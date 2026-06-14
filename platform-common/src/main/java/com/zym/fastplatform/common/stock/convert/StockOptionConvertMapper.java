package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockOption;
import com.zym.fastplatform.common.stock.entity.dto.StockOptionDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockOptionVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockOptionConvertMapper extends BaseConvertMapper<StockOption, StockOptionVO, StockOptionDTO> {


}