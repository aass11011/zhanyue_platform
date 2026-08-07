package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.common.stock.entity.StockConcept;
import com.zym.fastplatform.common.stock.entity.dto.StockConceptDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockConceptVO;
import com.zym.fastplatform.common.stock.service.StockConceptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/concept")
public class StockConceptController extends BaseController<StockConceptService, StockConcept, StockConceptDTO, StockConceptVO> {
}
