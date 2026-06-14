package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockMarketDataConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockMarketDataDao;
import com.zym.fastplatform.common.stock.entity.StockMarketData;
import com.zym.fastplatform.common.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMarketDataVO;
import com.zym.fastplatform.common.stock.service.StockMarketDataService;
import org.springframework.stereotype.Service;


@Service
public class StockMarketDataServiceImpl extends BaseServiceImpl<StockMarketDataDao, StockMarketData, StockMarketDataConvertMapper, StockMarketDataDTO, StockMarketDataVO> implements StockMarketDataService {



}