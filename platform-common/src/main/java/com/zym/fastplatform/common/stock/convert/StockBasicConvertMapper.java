// 文件路径: src/main/java/com/zym/fastplatform/stock/convert/StockBasicConvertMapper.java
package com.zym.fastplatform.common.stock.convert;

import com.zym.fastplatform.common.common.framework.convert.BaseConvertMapper;
import com.zym.fastplatform.common.stock.entity.StockBasic;
import com.zym.fastplatform.common.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBasicVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockBasicConvertMapper extends BaseConvertMapper<StockBasic, StockBasicVO, StockBasicDTO> {
}
