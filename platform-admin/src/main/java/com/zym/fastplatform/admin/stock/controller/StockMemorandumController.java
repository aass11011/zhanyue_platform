package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.stock.entity.StockMemorandum;
import com.zym.fastplatform.common.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumMonthVO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumVO;
import com.zym.fastplatform.common.stock.service.StockMemorandumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/stock/memorandum")
@Slf4j
public class StockMemorandumController extends BaseController<StockMemorandumService, StockMemorandum, StockMemorandumDTO, StockMemorandumVO> {

    @GetMapping("/current-month-status")
    public Result<StockMemorandumMonthVO> checkCurrentMonthDataExists(@RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth) {
        return Result.ok(service.checkCurrentMonthDataExists(yearMonth));
    }
}
