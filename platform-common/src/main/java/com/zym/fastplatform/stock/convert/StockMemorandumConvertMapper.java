package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockMemorandum;
import com.zym.fastplatform.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.stock.entity.vo.StockMemorandumVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMemorandumConvertMapper extends BaseConvertMapper<StockMemorandum, StockMemorandumVO, StockMemorandumDTO> {
}
