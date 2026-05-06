package com.zym.fastplatform.stock.dao;

import com.zym.fastplatform.framework.dao.BaseDao;
import com.zym.fastplatform.stock.entity.StockTradeDetail;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockTradeDetailDao extends BaseDao<StockTradeDetail> {
    void deleteByStockCodeAndTradeDate(String stockCode, LocalDate tradeDate);

    List<StockTradeDetail> findByStockCodeAndTradeDate(String code, LocalDate yesterday);

    int deleteByTradeDateBefore(LocalDate tradeDateBefore);
}
