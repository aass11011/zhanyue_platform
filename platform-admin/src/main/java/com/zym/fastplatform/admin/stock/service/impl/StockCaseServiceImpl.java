package com.zym.fastplatform.admin.stock.service.impl;

import com.zym.fastplatform.admin.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockCaseConvertMapper;
import com.zym.fastplatform.stock.dao.StockCaseDao;
import com.zym.fastplatform.stock.entity.StockCase;
import com.zym.fastplatform.stock.entity.dto.StockCaseDTO;
import com.zym.fastplatform.stock.entity.vo.StockCaseVO;
import com.zym.fastplatform.admin.stock.service.StockCaseService;
import org.springframework.stereotype.Service;


@Service
public class StockCaseServiceImpl extends BaseServiceImpl<StockCaseDao, StockCase, StockCaseConvertMapper, StockCaseDTO, StockCaseVO> implements StockCaseService {



}