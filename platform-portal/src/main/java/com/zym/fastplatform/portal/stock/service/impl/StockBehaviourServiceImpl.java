package com.zym.fastplatform.portal.stock.service.impl;

import com.zym.fastplatform.portal.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.portal.stock.service.StockBehaviourService;
import com.zym.fastplatform.stock.convert.StockBehaviourConvertMapper;
import com.zym.fastplatform.stock.dao.StockBehaviourDao;
import com.zym.fastplatform.stock.entity.StockBehaviour;
import com.zym.fastplatform.stock.entity.dto.StockBehaviourDTO;
import com.zym.fastplatform.stock.entity.vo.StockBehaviourVO;
import org.springframework.stereotype.Service;

@Service
public class StockBehaviourServiceImpl extends BaseServiceImpl<StockBehaviourDao, StockBehaviour, StockBehaviourConvertMapper, StockBehaviourDTO, StockBehaviourVO> implements StockBehaviourService {
}
