package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockCollectGroup;
import com.zym.fastplatform.stock.entity.dto.StockCollectGroupDTO;
import com.zym.fastplatform.stock.entity.vo.StockCollectGroupVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockCollectGroupConvertMapper extends BaseConvertMapper<StockCollectGroup, StockCollectGroupVO, StockCollectGroupDTO> {
}
