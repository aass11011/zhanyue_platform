package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockMarketFieldConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockMarketFieldDao;
import com.zym.fastplatform.common.stock.entity.StockMarketField;
import com.zym.fastplatform.common.stock.entity.dto.StockMarketFieldDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMarketFieldVO;
import com.zym.fastplatform.common.stock.service.StockMarketFieldService;
import org.springframework.stereotype.Service;


@Service
public class StockMarketFieldServiceImpl extends BaseServiceImpl<StockMarketFieldDao, StockMarketField, StockMarketFieldConvertMapper, StockMarketFieldDTO, StockMarketFieldVO> implements StockMarketFieldService {



}