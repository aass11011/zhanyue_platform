package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockMarketRecord;
import com.zym.fastplatform.stock.entity.dto.StockMarketRecordDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketRecordVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {StockMarketDataConvertMapper.class})
public interface StockMarketRecordConvertMapper extends BaseConvertMapper<StockMarketRecord, StockMarketRecordVO, StockMarketRecordDTO> {


}