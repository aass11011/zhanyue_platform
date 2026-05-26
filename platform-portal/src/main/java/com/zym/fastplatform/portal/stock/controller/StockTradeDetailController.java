package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.portal.stock.service.StockTradeDetailService;
import com.zym.fastplatform.stock.entity.StockTradeDetail;
import com.zym.fastplatform.stock.entity.dto.StockTradeDetailDTO;
import com.zym.fastplatform.stock.entity.vo.StockTradeDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/stock/trade/detail")
public class StockTradeDetailController extends BaseController<StockTradeDetailService, StockTradeDetail, StockTradeDetailDTO, StockTradeDetailVO> {
}
