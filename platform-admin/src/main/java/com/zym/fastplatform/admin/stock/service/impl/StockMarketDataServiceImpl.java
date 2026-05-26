package com.zym.fastplatform.admin.stock.service.impl;

import com.zym.fastplatform.admin.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockMarketDataConvertMapper;
import com.zym.fastplatform.stock.dao.StockMarketDataDao;
import com.zym.fastplatform.stock.entity.StockMarketData;
import com.zym.fastplatform.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketDataVO;
import com.zym.fastplatform.admin.stock.service.StockMarketDataService;
import org.springframework.stereotype.Service;


@Service
public class StockMarketDataServiceImpl extends BaseServiceImpl<StockMarketDataDao, StockMarketData, StockMarketDataConvertMapper, StockMarketDataDTO, StockMarketDataVO> implements StockMarketDataService {



}