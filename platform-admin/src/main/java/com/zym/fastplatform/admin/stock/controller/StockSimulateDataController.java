package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockSimulateData;
import com.zym.fastplatform.stock.entity.dto.StockSimulateDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockSimulateDataVO;
import com.zym.fastplatform.stock.service.StockSimulateDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/simulate/data")
@Slf4j
public class StockSimulateDataController extends BaseController<StockSimulateDataService, StockSimulateData, StockSimulateDataDTO, StockSimulateDataVO> {


}