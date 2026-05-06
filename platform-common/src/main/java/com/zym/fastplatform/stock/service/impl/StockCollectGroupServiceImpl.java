package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockCollectGroupConvertMapper;
import com.zym.fastplatform.stock.dao.StockCollectGroupDao;
import com.zym.fastplatform.stock.entity.StockCollectGroup;
import com.zym.fastplatform.stock.entity.dto.StockCollectGroupDTO;
import com.zym.fastplatform.stock.entity.vo.StockCollectGroupVO;
import com.zym.fastplatform.stock.service.StockCollectGroupSerivce;
import org.springframework.stereotype.Service;

@Service
public class StockCollectGroupServiceImpl extends BaseServiceImpl<StockCollectGroupDao,StockCollectGroup, StockCollectGroupConvertMapper, StockCollectGroupDTO, StockCollectGroupVO> implements StockCollectGroupSerivce {

}
