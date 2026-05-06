package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockSseFunds;
import com.zym.fastplatform.stock.entity.dto.StockSseFundsDTO;
import com.zym.fastplatform.stock.entity.vo.StockSseFundsVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockSseFundsConvertMapper extends BaseConvertMapper<StockSseFunds, StockSseFundsVO, StockSseFundsDTO> {


}