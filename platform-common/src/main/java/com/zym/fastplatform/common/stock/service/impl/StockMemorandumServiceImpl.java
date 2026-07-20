package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockMemorandumConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockMemorandumDao;
import com.zym.fastplatform.common.stock.entity.StockMemorandum;
import com.zym.fastplatform.common.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumMonthVO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumVO;
import com.zym.fastplatform.common.stock.service.StockMemorandumService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockMemorandumServiceImpl extends BaseServiceImpl<StockMemorandumDao, StockMemorandum, StockMemorandumConvertMapper, StockMemorandumDTO, StockMemorandumVO> implements StockMemorandumService {

    @Override
    public StockMemorandumMonthVO checkCurrentMonthDataExists(YearMonth yearMonth) {
        if(yearMonth == null) {
            yearMonth = YearMonth.now();
        }
        StockMemorandumMonthVO vo = new StockMemorandumMonthVO();
        int daysInMonth = yearMonth.lengthOfMonth();
        
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        
        List<StockMemorandum> records = dao.findByRememberDateBetween(startDate, endDate);
        
        Map<Integer, List<String>> dayStockMap = new HashMap<>();
        for (StockMemorandum record : records) {
            LocalDate recordDate = record.getRememberDate();
            int dayOfMonth = recordDate.getDayOfMonth();
            dayStockMap.computeIfAbsent(dayOfMonth, k -> new ArrayList<>()).add(record.getStockName());
        }
        
        List<Boolean> existList = new ArrayList<>();
        List<String> stockList = new ArrayList<>();
        for (int i = 1; i <= daysInMonth; i++) {
            List<String> stocks = dayStockMap.get(i);
            if (stocks != null && !stocks.isEmpty()) {
                existList.add(true);
                stockList.add(String.join(",", stocks));
            } else {
                existList.add(false);
                stockList.add("");
            }
        }
        
        vo.setMemorandumExistList(existList);
        vo.setMemorandumStockList(stockList);
        return vo;
    }
}
