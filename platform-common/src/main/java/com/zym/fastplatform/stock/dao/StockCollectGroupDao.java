package com.zym.fastplatform.stock.dao;

import com.zym.fastplatform.framework.dao.NoStatusBaseDao;
import com.zym.fastplatform.stock.entity.StockCollectGroup;
import org.springframework.stereotype.Repository;
@Repository
public interface StockCollectGroupDao extends NoStatusBaseDao<StockCollectGroup> {
    StockCollectGroup findOneByUserIdAndIsDefault(Long userId, Boolean isDefault);
}
