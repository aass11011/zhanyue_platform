package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockSseFunds;
import com.zym.fastplatform.common.stock.entity.dto.StockSseFundsDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockSseFundsVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockSseFundsConvertMapper extends BaseConvertMapper<StockSseFunds, StockSseFundsVO, StockSseFundsDTO> {


}