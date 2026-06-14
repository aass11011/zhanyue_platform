package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockCaseConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockCaseDao;
import com.zym.fastplatform.common.stock.entity.StockCase;
import com.zym.fastplatform.common.stock.entity.dto.StockCaseDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCaseVO;
import com.zym.fastplatform.common.stock.service.StockCaseService;
import org.springframework.stereotype.Service;


@Service
public class StockCaseServiceImpl extends BaseServiceImpl<StockCaseDao, StockCase, StockCaseConvertMapper, StockCaseDTO, StockCaseVO> implements StockCaseService {



}