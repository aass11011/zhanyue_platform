package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockCollectItemConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockCollectGroupDao;
import com.zym.fastplatform.common.stock.dao.StockCollectItemDao;
import com.zym.fastplatform.common.stock.entity.StockCollectGroup;
import com.zym.fastplatform.common.stock.entity.StockCollectItem;
import com.zym.fastplatform.common.stock.entity.dto.StockCollectItemDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCollectItemVO;
import com.zym.fastplatform.common.stock.service.StockCollectItemSerivce;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockCollectItemSerivceImpl extends BaseServiceImpl<StockCollectItemDao,  StockCollectItem, StockCollectItemConvertMapper, StockCollectItemDTO,StockCollectItemVO> implements StockCollectItemSerivce {
    @Autowired
    private StockCollectGroupDao stockCollectGroupDao;
    @Override
    public List<String> getFavoriteList() {
        Long id = getUser().getId();
        List<String> stockCodes = dao.findAllByUserIdAndDefaultGroup(id);
        return stockCodes;
    }

    @Override
    @Transactional
    public void removeFavorite(String code) {
        StockCollectGroup group = judgeAndGetCollectGroup();
        dao.deleteByStockCodeAndGroupId(code, group.getId());
    }

    @Override
    public void addFavorite(String code) {
        StockCollectGroup group = judgeAndGetCollectGroup();
        StockCollectItem item = new StockCollectItem();
        item.setStockCode(code);
        item.setGroupId(group.getId());
        dao.save(item);
    }

    @NotNull
    private StockCollectGroup judgeAndGetCollectGroup() {
        StockCollectGroup group = stockCollectGroupDao.findOneByUserIdAndIsDefault(getUser().getId(), true);
        if(group == null) {
            group = new StockCollectGroup();
            group.setUserId(getUser().getId());
            group.setGroupName("默认收藏");
            group.setIsDefault(true);
            group.setCreatedBy(getUser().getUsername());
            stockCollectGroupDao.save(group);
        }
        return group;
    }
}
