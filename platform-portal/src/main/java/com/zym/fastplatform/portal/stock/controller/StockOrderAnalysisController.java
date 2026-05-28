package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockTradeDay;
import com.zym.fastplatform.stock.entity.StockTradeDetail;
import com.zym.fastplatform.stock.entity.dto.StockTradeDayDTO;
import com.zym.fastplatform.stock.entity.dto.StockTradeDetailDTO;
import com.zym.fastplatform.stock.entity.vo.StockTradeDetailVO;
import com.zym.fastplatform.stock.service.StockTradeDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stock/order/analysis")
public class StockOrderAnalysisController extends BaseController<StockTradeDetailService, StockTradeDetail, StockTradeDetailDTO, StockTradeDetailVO> {

    @GetMapping("list/two/week")
    public Result<List<StockTradeDay>> listTwoWeek(StockTradeDayDTO stockTradeDayDTO) {
        return Result.ok(service.getStockTradeDayTwoWeekLatest(stockTradeDayDTO.getStockCode()));
    }
}
