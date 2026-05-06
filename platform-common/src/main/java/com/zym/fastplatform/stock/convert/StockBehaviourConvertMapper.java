package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockBehaviour;
import com.zym.fastplatform.stock.entity.dto.StockBehaviourDTO;
import com.zym.fastplatform.stock.entity.vo.StockBehaviourVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {StockBehaviourStickyConvertMapper.class})
public interface StockBehaviourConvertMapper extends BaseConvertMapper<StockBehaviour, StockBehaviourVO, StockBehaviourDTO> {


}