package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockOptionConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockOptionDao;
import com.zym.fastplatform.common.stock.entity.StockOption;
import com.zym.fastplatform.common.stock.entity.dto.StockOptionDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockOptionVO;
import com.zym.fastplatform.common.stock.service.StockOptionService;
import org.springframework.stereotype.Service;


@Service
public class StockOptionServiceImpl extends BaseServiceImpl<StockOptionDao, StockOption, StockOptionConvertMapper, StockOptionDTO, StockOptionVO> implements StockOptionService {



}