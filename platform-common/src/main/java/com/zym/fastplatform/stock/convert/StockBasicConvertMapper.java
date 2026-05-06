// 文件路径: src/main/java/com/zym/fastplatform/stock/convert/StockBasicConvertMapper.java
package com.zym.fastplatform.stock.convert;

import com.zym.fastplatform.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.stock.entity.StockBasic;
import com.zym.fastplatform.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.stock.entity.vo.StockBasicVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockBasicConvertMapper extends BaseConvertMapper<StockBasic, StockBasicVO, StockBasicDTO> {
}
