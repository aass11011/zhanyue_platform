package com.zym.fastplatform.stock.service;

import com.zym.fastplatform.framework.service.BaseService;
import com.zym.fastplatform.stock.entity.StockSimulate;
import com.zym.fastplatform.stock.entity.dto.StockSimulateDTO;
import com.zym.fastplatform.stock.entity.vo.StockSimulateVO;
import org.springframework.web.multipart.MultipartFile;

public interface StockSimulateService extends BaseService<StockSimulate, StockSimulateVO, StockSimulateDTO> {


    void importData(MultipartFile file, Long id);
}