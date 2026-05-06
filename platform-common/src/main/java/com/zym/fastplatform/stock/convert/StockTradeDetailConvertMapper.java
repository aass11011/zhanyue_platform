package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockTradeDetail;
import com.zym.fastplatform.stock.entity.dto.StockTradeDetailDTO;
import com.zym.fastplatform.stock.entity.vo.StockTradeDetailVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockTradeDetailConvertMapper extends BaseConvertMapper<StockTradeDetail, StockTradeDetailVO, StockTradeDetailDTO> {
}
