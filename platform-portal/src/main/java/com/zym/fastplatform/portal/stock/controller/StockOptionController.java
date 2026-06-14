package com.zym.fastplatform.portal.stock.controller;

import com.zym.fastplatform.portal.framework.controller.BaseController;
import com.zym.fastplatform.common.stock.entity.StockOption;
import com.zym.fastplatform.common.stock.entity.dto.StockOptionDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockOptionVO;
import com.zym.fastplatform.common.stock.service.StockOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/option")
@Slf4j
public class StockOptionController extends BaseController<StockOptionService, StockOption, StockOptionDTO, StockOptionVO> {
}
