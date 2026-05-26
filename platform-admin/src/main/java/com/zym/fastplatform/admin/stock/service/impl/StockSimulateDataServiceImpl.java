package com.zym.fastplatform.admin.stock.service.impl;

import com.zym.fastplatform.admin.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockSimulateDataConvertMapper;
import com.zym.fastplatform.stock.dao.StockSimulateDataDao;
import com.zym.fastplatform.stock.entity.StockSimulateData;
import com.zym.fastplatform.stock.entity.dto.StockSimulateDataDTO;
import com.zym.fastplatform.stock.entity.vo.StockSimulateDataVO;
import com.zym.fastplatform.admin.stock.service.StockSimulateDataService;
import org.springframework.stereotype.Service;


@Service
public class StockSimulateDataServiceImpl extends BaseServiceImpl<StockSimulateDataDao, StockSimulateData, StockSimulateDataConvertMapper, StockSimulateDataDTO, StockSimulateDataVO> implements StockSimulateDataService {



}