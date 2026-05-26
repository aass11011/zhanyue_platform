package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockMarketSchema;
import com.zym.fastplatform.stock.entity.dto.StockMarketSchemaDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketSchemaVO;
import com.zym.fastplatform.admin.stock.service.StockMarketSchemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/market/schema")
@Slf4j
public class StockMarketSchemaController extends BaseController<StockMarketSchemaService, StockMarketSchema, StockMarketSchemaDTO, StockMarketSchemaVO> {


}