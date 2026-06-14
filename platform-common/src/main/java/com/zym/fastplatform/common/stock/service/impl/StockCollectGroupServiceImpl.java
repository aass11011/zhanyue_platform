package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockCollectGroupConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockCollectGroupDao;
import com.zym.fastplatform.common.stock.entity.StockCollectGroup;
import com.zym.fastplatform.common.stock.entity.dto.StockCollectGroupDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockCollectGroupVO;
import com.zym.fastplatform.common.stock.service.StockCollectGroupSerivce;
import org.springframework.stereotype.Service;

@Service
public class StockCollectGroupServiceImpl extends BaseServiceImpl<StockCollectGroupDao,StockCollectGroup, StockCollectGroupConvertMapper, StockCollectGroupDTO, StockCollectGroupVO> implements StockCollectGroupSerivce {

}
