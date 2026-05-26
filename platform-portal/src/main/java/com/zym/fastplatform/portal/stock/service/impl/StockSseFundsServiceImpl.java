package com.zym.fastplatform.portal.stock.service.impl;

import com.zym.fastplatform.portal.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.portal.stock.service.StockSseFundsService;
import com.zym.fastplatform.stock.convert.StockSseFundsConvertMapper;
import com.zym.fastplatform.stock.dao.StockSseFundsDao;
import com.zym.fastplatform.stock.entity.StockSseFunds;
import com.zym.fastplatform.stock.entity.dto.StockSseFundsDTO;
import com.zym.fastplatform.stock.entity.vo.StockSseFundsVO;
import org.springframework.stereotype.Service;

@Service
public class StockSseFundsServiceImpl extends BaseServiceImpl<StockSseFundsDao, StockSseFunds, StockSseFundsConvertMapper, StockSseFundsDTO, StockSseFundsVO> implements StockSseFundsService {
}
