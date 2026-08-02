package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockConcept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockConceptDao extends BaseDao<StockConcept> {
    List<StockConcept> findByStockCodeIn(List<String> stockCodes);

    void deleteByStockCode(String stockCode);
}