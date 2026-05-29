package com.zym.fastplatform.stock.dao;

import com.zym.fastplatform.framework.dao.BaseDao;
import com.zym.fastplatform.stock.entity.StockCollectGroup;
import org.springframework.stereotype.Repository;
@Repository
public interface StockCollectGroupDao extends BaseDao<StockCollectGroup> {
    StockCollectGroup findOneByUserIdAndIsDefault(Long userId, Boolean isDefault);
}
