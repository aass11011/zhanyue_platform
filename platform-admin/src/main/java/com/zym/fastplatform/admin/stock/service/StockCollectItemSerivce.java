package com.zym.fastplatform.admin.stock.service;

import com.zym.fastplatform.admin.framework.service.BaseService;
import com.zym.fastplatform.stock.entity.StockCollectItem;
import com.zym.fastplatform.stock.entity.dto.StockCollectItemDTO;
import com.zym.fastplatform.stock.entity.vo.StockCollectItemVO;

import java.util.List;

public interface StockCollectItemSerivce extends BaseService<StockCollectItem, StockCollectItemVO, StockCollectItemDTO> {
    List<String> getFavoriteList();

    void removeFavorite(String code);

    void addFavorite(String code);
}
