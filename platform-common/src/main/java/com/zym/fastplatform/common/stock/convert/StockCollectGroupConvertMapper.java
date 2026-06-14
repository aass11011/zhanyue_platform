package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockCollectGroup;
import com.zym.fastplatform.common.stock.entity.dto.StockCollectGroupDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCollectGroupVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockCollectGroupConvertMapper extends BaseConvertMapper<StockCollectGroup, StockCollectGroupVO, StockCollectGroupDTO> {
}
