package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockOptionConvertMapper;
import com.zym.fastplatform.stock.dao.StockOptionDao;
import com.zym.fastplatform.stock.entity.StockOption;
import com.zym.fastplatform.stock.entity.dto.StockOptionDTO;
import com.zym.fastplatform.stock.entity.vo.StockOptionVO;
import com.zym.fastplatform.stock.service.StockOptionService;
import org.springframework.stereotype.Service;


@Service
public class StockOptionServiceImpl extends BaseServiceImpl<StockOptionDao, StockOption, StockOptionConvertMapper, StockOptionDTO, StockOptionVO> implements StockOptionService {



}