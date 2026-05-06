package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockCollectItem;
import com.zym.fastplatform.stock.entity.dto.StockCollectItemDTO;
import com.zym.fastplatform.stock.entity.vo.StockCollectItemVO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface StockCollectItemConvertMapper extends BaseConvertMapper<StockCollectItem, StockCollectItemVO, StockCollectItemDTO> {
}
