package com.zym.fastplatform.common.stock.service;

import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.stock.entity.StockBehaviour;
import com.zym.fastplatform.common.stock.entity.dto.StockBehaviourDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBasicVO;
import com.zym.fastplatform.common.stock.entity.vo.StockBehaviourVO;

import java.util.List;

public interface StockBehaviourService extends BaseService<StockBehaviour, StockBehaviourVO, StockBehaviourDTO> {


    List<StockBasicVO> listStock();
}