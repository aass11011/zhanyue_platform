package com.zym.fastplatform.portal.stock.service.impl;

import com.zym.fastplatform.portal.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.portal.stock.service.StockMarketDataService;
import com.zym.fastplatform.stock.convert.StockMarketDataConvertMapper;
import com.zym.fastplatform.stock.dao.StockMarketDataDao;
import com.zym.fastplatform.stock.entity.StockMarketData;
import com.zym.fastplatform.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketDataVO;
import org.springframework.stereotype.Service;

@Service
public class StockMarketDataServiceImpl extends BaseServiceImpl<StockMarketDataDao, StockMarketData, StockMarketDataConvertMapper, StockMarketDataDTO, StockMarketDataVO> implements StockMarketDataService {
}
