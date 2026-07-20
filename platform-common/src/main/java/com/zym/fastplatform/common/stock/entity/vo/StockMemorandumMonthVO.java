package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockMemorandumMonthVO {
    private List<Boolean> memorandumExistList;
    private List<String> memorandumStockList;
}
