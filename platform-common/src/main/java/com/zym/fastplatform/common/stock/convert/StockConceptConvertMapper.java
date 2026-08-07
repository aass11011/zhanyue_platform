package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockConcept;
import com.zym.fastplatform.common.stock.entity.dto.StockConceptDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockConceptVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockConceptConvertMapper extends BaseConvertMapper<StockConcept, StockConceptVO, StockConceptDTO> {
}
