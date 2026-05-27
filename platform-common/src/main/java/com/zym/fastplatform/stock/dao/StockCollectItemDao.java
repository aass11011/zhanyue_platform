package com.zym.fastplatform.stock.dao;

import com.zym.fastplatform.framework.dao.BaseDao;
import com.zym.fastplatform.framework.dao.NoStatusBaseDao;
import com.zym.fastplatform.stock.entity.StockCollectItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockCollectItemDao extends NoStatusBaseDao<StockCollectItem> {
    @Query("select s.stockCode from StockCollectItem s join StockCollectGroup  g on s.groupId = g.id where g.userId = ?1 and g.isDefault = true")
    List<String> findAllByUserIdAndDefaultGroup(Long id);

    void deleteByStockCodeAndGroupId(String code, Long id);
}
