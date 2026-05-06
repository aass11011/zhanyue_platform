package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockOption;
import com.zym.fastplatform.stock.entity.dto.StockOptionDTO;
import com.zym.fastplatform.stock.entity.vo.StockOptionVO;
import com.zym.fastplatform.stock.service.StockOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/option")
@Slf4j
public class StockOptionController extends BaseController<StockOptionService, StockOption, StockOptionDTO, StockOptionVO> {


}