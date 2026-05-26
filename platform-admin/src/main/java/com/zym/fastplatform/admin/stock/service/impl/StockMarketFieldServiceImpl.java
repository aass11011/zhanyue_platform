package com.zym.fastplatform.admin.stock.service.impl;

import com.zym.fastplatform.admin.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockMarketFieldConvertMapper;
import com.zym.fastplatform.stock.dao.StockMarketFieldDao;
import com.zym.fastplatform.stock.entity.StockMarketField;
import com.zym.fastplatform.stock.entity.dto.StockMarketFieldDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketFieldVO;
import com.zym.fastplatform.admin.stock.service.StockMarketFieldService;
import org.springframework.stereotype.Service;


@Service
public class StockMarketFieldServiceImpl extends BaseServiceImpl<StockMarketFieldDao, StockMarketField, StockMarketFieldConvertMapper, StockMarketFieldDTO, StockMarketFieldVO> implements StockMarketFieldService {



}