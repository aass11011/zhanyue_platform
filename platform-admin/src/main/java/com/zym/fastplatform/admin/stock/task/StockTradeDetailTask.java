package com.zym.fastplatform.admin.stock.task;

import com.zym.fastplatform.common.stock.dao.StockSseFundsDao;
import com.zym.fastplatform.common.stock.dao.StockTradeDetailDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class StockTradeDetailTask {

    @Autowired
    private StockTradeDetailDao stockTradeDetailDao;
    @Autowired
    private StockSseFundsDao stockSseFundsDao;
    /**
     * 每天凌晨1点执行，删除2个月之前的StockTradeDetail数据
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void cleanupOldTradeDetails() {
        log.info("开始执行StockTradeDetail数据清理任务");
        LocalDate twoMonthsAgo = LocalDate.now().minusMonths(2);
        try {
            // 删除tradeDate早于2个月前的数据
            int deletedCount = stockTradeDetailDao.deleteByTradeDateBefore(twoMonthsAgo);
            log.info("清理完成，删除了{}条2个月之前的StockTradeDetail数据", deletedCount);
        } catch (Exception e) {
            log.error("清理StockTradeDetail数据时发生错误", e);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupSseData(){
        log.info("开始执行Sse数据清理任务");
        LocalDate halfYearsAgo = LocalDate.now().minusMonths(6).minusDays(1);
        try {
            int deletedCount = stockSseFundsDao.deleteByStatDateBefore(halfYearsAgo);
            log.info("清理完成，删除了{}条SSE数据", deletedCount);
        } catch (Exception e) {
            log.error("清理SSE数据时发生错误", e);
        }
    }
}
