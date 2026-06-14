package com.zym.fastplatform.common.stock.dao;

import com.zym.fastplatform.common.common.framework.dao.BaseDao;
import com.zym.fastplatform.common.stock.entity.StockCollectGroup;
import org.springframework.stereotype.Repository;
@Repository
public interface StockCollectGroupDao extends BaseDao<StockCollectGroup> {
    StockCollectGroup findOneByUserIdAndIsDefault(Long userId, Boolean isDefault);
}
