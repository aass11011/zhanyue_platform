package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;

import com.zym.fastplatform.common.stock.convert.StockConceptConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockConceptDao;
import com.zym.fastplatform.common.stock.entity.StockConcept;
import com.zym.fastplatform.common.stock.entity.dto.StockConceptDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockConceptVO;
import com.zym.fastplatform.common.stock.service.StockConceptService;
import org.springframework.stereotype.Service;

@Service
public class StockConceptServiceImpl extends BaseServiceImpl<StockConceptDao, StockConcept, StockConceptConvertMapper, StockConceptDTO, StockConceptVO> implements StockConceptService {

}