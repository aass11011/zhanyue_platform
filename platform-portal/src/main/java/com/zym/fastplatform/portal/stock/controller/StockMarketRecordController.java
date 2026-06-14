package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.common.stock.entity.StockMarketRecord;
import com.zym.fastplatform.common.stock.entity.dto.StockMarketRecordDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMarketRecordVO;
import com.zym.fastplatform.common.stock.service.StockMarketRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/market/record")
@Slf4j
public class StockMarketRecordController extends BaseController<StockMarketRecordService, StockMarketRecord, StockMarketRecordDTO, StockMarketRecordVO> {
}
