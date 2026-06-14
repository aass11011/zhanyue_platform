package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockSimulateDataConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockSimulateDataDao;
import com.zym.fastplatform.common.stock.entity.StockSimulateData;
import com.zym.fastplatform.common.stock.entity.dto.StockSimulateDataDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockSimulateDataVO;
import com.zym.fastplatform.common.stock.service.StockSimulateDataService;
import org.springframework.stereotype.Service;


@Service
public class StockSimulateDataServiceImpl extends BaseServiceImpl<StockSimulateDataDao, StockSimulateData, StockSimulateDataConvertMapper, StockSimulateDataDTO, StockSimulateDataVO> implements StockSimulateDataService {



}