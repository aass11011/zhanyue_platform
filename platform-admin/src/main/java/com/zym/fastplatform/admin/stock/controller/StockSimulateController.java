package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.stock.entity.StockSimulate;
import com.zym.fastplatform.stock.entity.dto.StockSimulateDTO;
import com.zym.fastplatform.stock.entity.vo.StockSimulateVO;
import com.zym.fastplatform.admin.stock.service.StockSimulateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/stock/simulate")
@Slf4j
public class StockSimulateController extends BaseController<StockSimulateService, StockSimulate, StockSimulateDTO, StockSimulateVO> {

    @PostMapping("import")
    public Result<Void> importData(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        service.importData(file, id);
        return Result.ok();
    }
}