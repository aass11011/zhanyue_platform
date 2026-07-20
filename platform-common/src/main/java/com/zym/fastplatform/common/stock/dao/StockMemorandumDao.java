package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockMemorandum;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockMemorandumDao extends BaseDao<StockMemorandum> {
    List<StockMemorandum> findByRememberDateBetween(LocalDate startDate, LocalDate endDate);
}
