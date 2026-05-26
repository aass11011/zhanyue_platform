package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.portal.stock.service.StockCaseService;
import com.zym.fastplatform.stock.entity.StockCase;
import com.zym.fastplatform.stock.entity.dto.StockCaseDTO;
import com.zym.fastplatform.stock.entity.vo.StockCaseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/case")
@Slf4j
public class StockCaseController extends BaseController<StockCaseService, StockCase, StockCaseDTO, StockCaseVO> {
}
