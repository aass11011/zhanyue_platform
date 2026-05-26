package com.zym.fastplatform.admin.stock.controller;

import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.stock.entity.StockCollectItem;
import com.zym.fastplatform.stock.entity.dto.StockCollectItemDTO;
import com.zym.fastplatform.stock.entity.vo.StockCollectItemVO;
import com.zym.fastplatform.admin.stock.service.StockCollectItemSerivce;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock/collect/item")
public class StockCollectItemController extends BaseController<StockCollectItemSerivce, StockCollectItem, StockCollectItemDTO, StockCollectItemVO> {
    @GetMapping("favorite/list")
    public Result<List<String>> getFavoriteList() {
        return Result.ok(service.getFavoriteList());
    }

    @PostMapping("/favorite/remove/{code}")
    public Result<Void> removeFavorite(@PathVariable String code) {
        service.removeFavorite(code);
        return Result.ok();
    }

    @PostMapping("/favorite/add/{code}")
    public Result<Void> addFavorite(@PathVariable String code) {
        service.addFavorite(code);
        return Result.ok();
    }
}
