package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.portal.stock.service.StockMarketDataService;
import com.zym.fastplatform.stock.entity.StockMarketData;
import com.zym.fastplatform.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketDataVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/market/data")
@Slf4j
public class StockMarketDataController extends BaseController<StockMarketDataService, StockMarketData, StockMarketDataDTO, StockMarketDataVO> {
}
