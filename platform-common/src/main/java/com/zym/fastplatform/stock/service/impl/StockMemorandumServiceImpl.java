package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockMemorandumConvertMapper;
import com.zym.fastplatform.stock.dao.StockMemorandumDao;
import com.zym.fastplatform.stock.entity.StockMemorandum;
import com.zym.fastplatform.stock.entity.dto.StockMemorandumDTO;
import com.zym.fastplatform.stock.entity.vo.StockMemorandumVO;
import com.zym.fastplatform.stock.service.StockMemorandumService;
import org.springframework.stereotype.Service;

@Service
public class StockMemorandumServiceImpl extends BaseServiceImpl<StockMemorandumDao, StockMemorandum, StockMemorandumConvertMapper, StockMemorandumDTO, StockMemorandumVO> implements StockMemorandumService {
}
