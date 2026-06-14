package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.common.stock.entity.StockSseFunds;
import com.zym.fastplatform.common.stock.entity.dto.StockSseFundsDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockSseFundsVO;
import com.zym.fastplatform.common.stock.service.StockSseFundsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/sse/funds")
@Slf4j
public class StockSseFundsController extends BaseController<StockSseFundsService, StockSseFunds, StockSseFundsDTO, StockSseFundsVO> {

}