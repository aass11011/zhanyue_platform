// 文件路径: src/main/java/com/zym/fastplatform/stock/service/StockBasicService.java
package com.zym.fastplatform.stock.service;

import com.zym.fastplatform.framework.service.BaseService;
import com.zym.fastplatform.stock.entity.StockBasic;
import com.zym.fastplatform.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.stock.entity.vo.StockBasicVO;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

public interface StockBasicService extends BaseService<StockBasic, StockBasicVO, StockBasicDTO> {
    void importData(MultipartFile file);

    void exportData(ServletOutputStream outputStream, StockBasicDTO stockBasicDTO);

    void fillExchangeAndMarket(String stockCode, StockBasic entity);

    void getByStockCode(String stockCode);
}
