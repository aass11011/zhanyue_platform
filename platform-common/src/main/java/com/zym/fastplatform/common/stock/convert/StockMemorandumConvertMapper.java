package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockMemorandum;
import com.zym.fastplatform.common.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMemorandumConvertMapper extends BaseConvertMapper<StockMemorandum, StockMemorandumVO, StockMemorandumDTO> {
}
