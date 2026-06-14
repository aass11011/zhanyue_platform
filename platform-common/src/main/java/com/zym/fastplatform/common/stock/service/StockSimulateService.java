package com.zym.fastplatform.common.stock.service;

import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.stock.entity.StockSimulate;
import com.zym.fastplatform.common.stock.entity.dto.StockSimulateDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockSimulateVO;
import org.springframework.web.multipart.MultipartFile;

public interface StockSimulateService extends BaseService<StockSimulate, StockSimulateVO, StockSimulateDTO> {


    void importData(MultipartFile file, Long id);
}