package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.stock.entity.StockBehaviour;
import com.zym.fastplatform.common.stock.entity.dto.StockBehaviourDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBasicVO;
import com.zym.fastplatform.common.stock.entity.vo.StockBehaviourVO;
import com.zym.fastplatform.common.stock.service.StockBehaviourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock/behaviour")
@Slf4j
public class StockBehaviourController extends BaseController<StockBehaviourService, StockBehaviour, StockBehaviourDTO, StockBehaviourVO> {

    @GetMapping("listStock")
    public Result<List<StockBasicVO>> listStock() {
        return Result.ok(service.listStock());
    }
}