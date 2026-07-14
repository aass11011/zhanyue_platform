package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockSseFunds;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StockSseFundsDao extends BaseDao<StockSseFunds> {


    int deleteByStatDateBefore(LocalDate statDateBefore);
}