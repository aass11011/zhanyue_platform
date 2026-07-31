// 文件路径: src/main/java/com/zym/fastplatform/stock/service/StockBasicService.java
package com.zym.fastplatform.common.stock.service;

import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.stock.entity.StockBasic;
import com.zym.fastplatform.common.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBasicVO;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StockBasicService extends BaseService<StockBasic, StockBasicVO, StockBasicDTO> {
    void importData(MultipartFile file);

    void exportData(ServletOutputStream outputStream, StockBasicDTO stockBasicDTO);

    void fillExchangeAndMarket(String stockCode, StockBasic entity);

    void getByStockCode(String stockCode);

    Map<String, List<StockBasicVO>> groupByLeadingStockConcept(String keyword);

    Map<String, List<StockBasicVO>> groupByConcept(String keyword);
}