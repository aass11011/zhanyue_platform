package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockMarketField;
import com.zym.fastplatform.stock.entity.dto.StockMarketFieldDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketFieldVO;
import com.zym.fastplatform.admin.stock.service.StockMarketFieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/market/field")
@Slf4j
public class StockMarketFieldController extends BaseController<StockMarketFieldService, StockMarketField, StockMarketFieldDTO, StockMarketFieldVO> {


}