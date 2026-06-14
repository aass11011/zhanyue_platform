package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockTradeDetail;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockTradeDetailDao extends BaseDao<StockTradeDetail> {
    void deleteByStockCodeAndTradeDate(String stockCode, LocalDate tradeDate);

    List<StockTradeDetail> findByStockCodeAndTradeDate(String code, LocalDate yesterday);

    int deleteByTradeDateBefore(LocalDate tradeDateBefore);
}
