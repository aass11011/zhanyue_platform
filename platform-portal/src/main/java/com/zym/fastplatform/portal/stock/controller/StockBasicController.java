package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockBasic;
import com.zym.fastplatform.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.stock.entity.vo.StockBasicVO;
import com.zym.fastplatform.stock.service.StockBasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/stock/basic")
public class StockBasicController extends BaseController<StockBasicService, StockBasic, StockBasicDTO, StockBasicVO>  {
}
