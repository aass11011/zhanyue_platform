package com.zym.fastplatform.portal.stock.service.impl;

import com.zym.fastplatform.portal.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.portal.stock.service.StockBasicService;
import com.zym.fastplatform.stock.convert.StockBasicConvertMapper;
import com.zym.fastplatform.stock.dao.StockBasicDao;
import com.zym.fastplatform.stock.entity.StockBasic;
import com.zym.fastplatform.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.stock.entity.vo.StockBasicVO;
import org.springframework.stereotype.Service;

@Service
public class StockBasicServiceImpl extends BaseServiceImpl<StockBasicDao, StockBasic, StockBasicConvertMapper, StockBasicDTO, StockBasicVO> implements StockBasicService {
}
