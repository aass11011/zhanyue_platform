package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockMarketRecord;
import com.zym.fastplatform.common.stock.entity.dto.StockMarketRecordDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMarketRecordVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {StockMarketDataConvertMapper.class})
public interface StockMarketRecordConvertMapper extends BaseConvertMapper<StockMarketRecord, StockMarketRecordVO, StockMarketRecordDTO> {


}