package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockBehaviour;
import com.zym.fastplatform.stock.entity.dto.StockBehaviourDTO;
import com.zym.fastplatform.stock.entity.vo.StockBehaviourVO;
import com.zym.fastplatform.stock.service.StockBehaviourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/behaviour")
@Slf4j
public class StockBehaviourController extends BaseController<StockBehaviourService, StockBehaviour, StockBehaviourDTO, StockBehaviourVO> {
}
