package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockCollectItem;
import com.zym.fastplatform.common.stock.entity.dto.StockCollectItemDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCollectItemVO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface StockCollectItemConvertMapper extends BaseConvertMapper<StockCollectItem, StockCollectItemVO, StockCollectItemDTO> {
}
