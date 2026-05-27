package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockMarketDataConvertMapper;
import com.zym.fastplatform.stock.dao.StockMarketDataDao;
import com.zym.fastplatform.stock.entity.StockMarketData;
import com.zym.fastplatform.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketDataVO;
import com.zym.fastplatform.stock.service.StockMarketDataService;
import org.springframework.stereotype.Service;


@Service
public class StockMarketDataServiceImpl extends BaseServiceImpl<StockMarketDataDao, StockMarketData, StockMarketDataConvertMapper, StockMarketDataDTO, StockMarketDataVO> implements StockMarketDataService {



}