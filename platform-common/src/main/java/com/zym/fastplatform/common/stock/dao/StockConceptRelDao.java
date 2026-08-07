package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockConceptRel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockConceptRelDao extends BaseDao<StockConceptRel> {
    List<StockConceptRel> findByStockCodeIn(List<String> stockCodes);

    void deleteByStockCode(String stockCode);
}