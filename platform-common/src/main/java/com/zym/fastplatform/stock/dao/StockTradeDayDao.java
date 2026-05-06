package com.zym.fastplatform.stock.dao;

import com.zym.fastplatform.framework.dao.BaseDao;
import com.zym.fastplatform.stock.entity.StockTradeDay;
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
