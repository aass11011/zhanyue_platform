package com.zym.fastplatform.common.stock.service;

import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.stock.entity.StockCollectItem;
import com.zym.fastplatform.common.stock.entity.dto.StockCollectItemDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCollectItemVO;

import java.util.List;

public interface StockCollectItemSerivce extends BaseService<StockCollectItem, StockCollectItemVO, StockCollectItemDTO> {
    List<String> getFavoriteList();

    void removeFavorite(String code);

    void addFavorite(String code);
}
