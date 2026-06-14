package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.stock.entity.StockMemorandum;
import com.zym.fastplatform.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.stock.entity.vo.StockMemorandumVO;
import com.zym.fastplatform.stock.service.StockMemorandumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock/memorandum")
@Slf4j
public class StockMemorandumController extends BaseController<StockMemorandumService, StockMemorandum, StockMemorandumDTO, StockMemorandumVO> {
}
