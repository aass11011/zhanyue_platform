package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockMemorandumConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockMemorandumDao;
import com.zym.fastplatform.common.stock.entity.StockMemorandum;
import com.zym.fastplatform.common.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockMemorandumVO;
import com.zym.fastplatform.common.stock.service.StockMemorandumService;
import org.springframework.stereotype.Service;

@Service
public class StockMemorandumServiceImpl extends BaseServiceImpl<StockMemorandumDao, StockMemorandum, StockMemorandumConvertMapper, StockMemorandumDTO, StockMemorandumVO> implements StockMemorandumService {
}
