package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.common.stock.entity.StockCase;
import com.zym.fastplatform.common.stock.entity.dto.StockCaseDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCaseVO;
import com.zym.fastplatform.common.stock.service.StockCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/case")
@Slf4j
public class StockCaseController extends BaseController<StockCaseService, StockCase, StockCaseDTO, StockCaseVO> {
}
