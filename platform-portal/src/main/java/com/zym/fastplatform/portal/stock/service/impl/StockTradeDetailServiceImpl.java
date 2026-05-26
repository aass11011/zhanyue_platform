package com.zym.fastplatform.portal.stock.service.impl;

import com.zym.fastplatform.portal.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.portal.stock.service.StockTradeDetailService;
import com.zym.fastplatform.stock.convert.StockTradeDetailConvertMapper;
import com.zym.fastplatform.stock.dao.StockTradeDetailDao;
import com.zym.fastplatform.stock.entity.StockTradeDetail;
import com.zym.fastplatform.stock.entity.dto.StockTradeDetailDTO;
import com.zym.fastplatform.stock.entity.vo.StockTradeDetailVO;
import org.springframework.stereotype.Service;

@Service
public class StockTradeDetailServiceImpl extends BaseServiceImpl<StockTradeDetailDao, StockTradeDetail, StockTradeDetailConvertMapper, StockTradeDetailDTO, StockTradeDetailVO> implements StockTradeDetailService {
}
