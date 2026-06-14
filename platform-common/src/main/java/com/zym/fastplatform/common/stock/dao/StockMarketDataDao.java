package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockMarketData;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StockMarketDataDao extends BaseDao<StockMarketData> {


    List<StockMarketData> findByRecordId(Long id);

    void deleteByRecordIdIn(Collection<Long> recordIds);
}