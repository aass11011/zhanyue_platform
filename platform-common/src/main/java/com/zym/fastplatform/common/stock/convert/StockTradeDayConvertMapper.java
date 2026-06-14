package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.stock.entity.StockTradeDay;
import com.zym.fastplatform.common.stock.entity.vo.StockTradeDayAnalysisVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockTradeDayConvertMapper {
    StockTradeDayAnalysisVO toAnalysisVO(StockTradeDay stockTradeDay);

    List<StockTradeDayAnalysisVO> toAnalysisVOList(List<StockTradeDay> stockTradeDayList);

}
