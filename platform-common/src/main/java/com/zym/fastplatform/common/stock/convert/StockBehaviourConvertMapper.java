package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockBehaviour;
import com.zym.fastplatform.common.stock.entity.dto.StockBehaviourDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBehaviourVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {StockBehaviourStickyConvertMapper.class})
public interface StockBehaviourConvertMapper extends BaseConvertMapper<StockBehaviour, StockBehaviourVO, StockBehaviourDTO> {


}