package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.framework.service.impl.NoStatusBaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockBehaviourStickyConvertMapper;
import com.zym.fastplatform.stock.dao.StockBehaviourStickyDao;
import com.zym.fastplatform.stock.entity.StockBehaviourSticky;
import com.zym.fastplatform.stock.entity.dto.StockBehaviourStickyDTO;
import com.zym.fastplatform.stock.entity.vo.StockBehaviourStickyVO;
import com.zym.fastplatform.stock.service.StockBehaviourStickyService;
import org.springframework.stereotype.Service;


@Service
public class StockBehaviourStickyServiceImpl extends NoStatusBaseServiceImpl<StockBehaviourStickyDao, StockBehaviourSticky, StockBehaviourStickyConvertMapper, StockBehaviourStickyDTO, StockBehaviourStickyVO> implements StockBehaviourStickyService {



}