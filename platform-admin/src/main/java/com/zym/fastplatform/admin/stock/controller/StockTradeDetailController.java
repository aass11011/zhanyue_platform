package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.stock.entity.vo.TradeDensityVO;
import com.zym.fastplatform.stock.entity.vo.StockTradeDayAnalysisVO;
import com.zym.fastplatform.stock.entity.vo.StockTradeEchartVO;
import com.zym.fastplatform.stock.entity.vo.StockTradeRangeAnalysisVO;
import com.zym.fastplatform.stock.service.StockTradeDetailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/stock/trade/detail")
public class StockTradeDetailController {
    @Autowired
    private StockTradeDetailService stockTradeDetailService;
    @Operation(summary = "导入历史数据")
    @PostMapping("import")
    public Result<Void> importData(@RequestParam Map<String, MultipartFile> fileMap){
        MultipartFile[] files = fileMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .filter(file -> file != null && !file.isEmpty())
                .toArray(MultipartFile[]::new);
        stockTradeDetailService.importData(files);
        return Result.ok();
    }
    @Operation(summary = "导入昨日数据")
    @PostMapping("import2")
    public Result<Void> importData2(@RequestParam Map<String, MultipartFile> fileMap){
        MultipartFile[] files = fileMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .filter(file -> file != null && !file.isEmpty())
                .toArray(MultipartFile[]::new);
        stockTradeDetailService.importData2(files);
        return Result.ok();
    }

    @Operation(summary = "导入今日数据")
    @PostMapping("import/today/one")
    public Result<Void> importTodayOne(@RequestParam MultipartFile file,@RequestParam("stockCode") String stockCode){
        stockTradeDetailService.importDataOne2(file,stockCode);
        return Result.ok();
    }

    @Operation(summary = "获取今日订单流")
    @GetMapping("data/day")
    public Result<StockTradeDayAnalysisVO> getStockTradeDay(@RequestParam("code") String stockCode, @RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return Result.ok(stockTradeDetailService.getStockTradeDay(stockCode,date));
    }
    @Operation(summary = "获取昨日订单流")
    @GetMapping("data/yesterday")
    public Result<StockTradeDayAnalysisVO> getStockTradeYesterday(@RequestParam("code") String code){
        return Result.ok(stockTradeDetailService.getStockTradeYesterday(code));
    }

    @Operation(summary = "今日订单图表数据")
    @GetMapping("/echart/day")
    public Result<List<StockTradeEchartVO>> getStockTradeEchartDay(@RequestParam("code") String stockCode, @RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return Result.ok(stockTradeDetailService.stockTradeEchartDay(stockCode,date));
    }
    @Operation(summary = "昨日订单图表数据")
    @GetMapping("/echart/yesterday")
    public Result<List<StockTradeEchartVO>> getStockTradeEchartYesterday(@RequestParam("code") String stockCode){
        return Result.ok(stockTradeDetailService.stockTradeEchartYesterday(stockCode));
    }
    @Operation(summary = "获取日期范围订单流")
    @GetMapping("data/range")
    public Result<StockTradeRangeAnalysisVO> getStockTradeRange(@RequestParam("code") String stockCode, @RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return Result.ok(stockTradeDetailService.stockTradeRange(stockCode,startDate,endDate));
    }

    @Operation(summary = "获取近三周缺失订单日期")
    @GetMapping("date/range/lack")
    public Result<List<LocalDate>> getStockTradeDateRangeLack(@RequestParam("code") String stockCode){
        return Result.ok(stockTradeDetailService.stockTradeDateRangeLack(stockCode));
    }

    @Operation(summary = "获取近三周存在订单日期")
    @GetMapping("date/range/exist")
    public Result<List<LocalDate>> getStockTradeRangeExist(@RequestParam("code") String stockCode){
        return Result.ok(stockTradeDetailService.stockTradeRangeExist(stockCode));
    }
    @Operation(summary = "获取单日交易密度")
    @GetMapping("density")
    public Result<List<TradeDensityVO>> getTradeDensity(@RequestParam("code") String stockCode,
                                                        @RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                        @RequestParam(value = "interval",defaultValue = "5")Integer interval){
        return Result.ok(stockTradeDetailService.getTradeDensity(stockCode,date,interval));
    }
}
