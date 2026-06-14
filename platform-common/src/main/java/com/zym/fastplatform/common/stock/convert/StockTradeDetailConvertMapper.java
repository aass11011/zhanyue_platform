package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockTradeDetail;
import com.zym.fastplatform.common.stock.entity.dto.StockTradeDetailDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockTradeDetailVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockTradeDetailConvertMapper extends BaseConvertMapper<StockTradeDetail, StockTradeDetailVO, StockTradeDetailDTO> {
}
