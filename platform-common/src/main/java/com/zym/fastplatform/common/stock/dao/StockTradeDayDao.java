package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockTradeDay;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockTradeDayDao extends BaseDao<StockTradeDay> {
    StockTradeDay findByStockCodeAndTradeDate(String code, LocalDate date);

    void deleteByStockCodeAndTradeDate(String stockCode, LocalDate tradeDate);

    List<StockTradeDay> findByStockCodeAndTradeDateBetween(@Size(max = 255) String stockCode, LocalDate tradeDateAfter, LocalDate tradeDateBefore);
}
