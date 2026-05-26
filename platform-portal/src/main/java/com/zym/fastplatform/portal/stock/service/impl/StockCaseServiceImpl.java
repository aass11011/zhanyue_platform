package com.zym.fastplatform.portal.stock.service.impl;

import com.zym.fastplatform.portal.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.portal.stock.service.StockCaseService;
import com.zym.fastplatform.stock.convert.StockCaseConvertMapper;
import com.zym.fastplatform.stock.dao.StockCaseDao;
import com.zym.fastplatform.stock.entity.StockCase;
import com.zym.fastplatform.stock.entity.dto.StockCaseDTO;
import com.zym.fastplatform.stock.entity.vo.StockCaseVO;
import org.springframework.stereotype.Service;

@Service
public class StockCaseServiceImpl extends BaseServiceImpl<StockCaseDao, StockCase, StockCaseConvertMapper, StockCaseDTO, StockCaseVO> implements StockCaseService {
}
