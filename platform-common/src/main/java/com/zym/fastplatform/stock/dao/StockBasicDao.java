// 文件路径: src/main/java/com/zym/fastplatform/stock/dao/StockBasicDao.java
package com.zym.fastplatform.stock.dao;

import com.zym.fastplatform.framework.dao.BaseDao;
import com.zym.fastplatform.stock.entity.StockBasic;
import org.springframework.stereotype.Repository;

@Repository
public interface StockBasicDao extends BaseDao<StockBasic> {
    StockBasic findByStockCode(String stockCode);

    boolean existsStockBasicByStockCode(String stockCode);
}
