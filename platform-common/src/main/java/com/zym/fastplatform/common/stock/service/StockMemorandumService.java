package com.zym.fastplatform.common.stock.service;

import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.stock.entity.StockMemorandum;
import com.zym.fastplatform.common.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumMonthVO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumVO;

import java.time.YearMonth;

public interface StockMemorandumService extends BaseService<StockMemorandum, StockMemorandumVO, StockMemorandumDTO> {
    StockMemorandumMonthVO checkCurrentMonthDataExists(YearMonth yearMonth);
}
