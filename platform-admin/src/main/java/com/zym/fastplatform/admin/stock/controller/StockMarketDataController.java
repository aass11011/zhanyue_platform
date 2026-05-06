package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.stock.entity.dto.StockMarketDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketDataVO;
import com.zym.fastplatform.stock.service.StockMarketDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock/market/data")
@Slf4j
public class StockMarketDataController {

    @Autowired
    private StockMarketDataService service;

    @GetMapping("listAll")
    public Result<List<StockMarketDataVO>> listAll(String sort, StockMarketDataDTO condition){
        return Result.ok(service.findAll(sort, condition));
    }
}
