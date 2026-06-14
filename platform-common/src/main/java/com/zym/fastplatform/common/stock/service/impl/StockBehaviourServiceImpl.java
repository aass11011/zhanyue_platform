package com.zym.fastplatform.common.stock.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.stock.convert.StockBehaviourConvertMapper;
import com.zym.fastplatform.common.stock.convert.StockBehaviourStickyConvertMapper;
import com.zym.fastplatform.common.stock.dao.StockBehaviourDao;
import com.zym.fastplatform.common.stock.dao.StockBehaviourStickyDao;
import com.zym.fastplatform.common.stock.entity.StockBehaviour;
import com.zym.fastplatform.common.stock.entity.StockBehaviourSticky;
import com.zym.fastplatform.common.stock.entity.dto.StockBehaviourDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBehaviourVO;
import com.zym.fastplatform.common.stock.service.StockBehaviourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;


@Service
public class StockBehaviourServiceImpl extends BaseServiceImpl<StockBehaviourDao, StockBehaviour, StockBehaviourConvertMapper, StockBehaviourDTO, StockBehaviourVO> implements StockBehaviourService {

    @Autowired
    private StockBehaviourStickyConvertMapper stockBehaviourStickyConvertMapper;
    @Autowired
    private StockBehaviourStickyDao stockBehaviourStickyDao;

    @Override
    @Transactional
    public void save(StockBehaviourDTO dto) {
        StockBehaviour stockBehaviour = convertMapper.toEntity(dto);
        List<StockBehaviourSticky> stockBehaviourStickyList = stockBehaviourStickyConvertMapper.toEntityList(dto.getStickFormList());
        dao.save(stockBehaviour);
        IntStream.range(0, stockBehaviourStickyList.size()).forEach(index -> {
            StockBehaviourSticky stockBehaviourSticky = stockBehaviourStickyList.get(index);
            stockBehaviourSticky.setBehaviourId(stockBehaviour.getId());
            stockBehaviourSticky.setImageUrl(String.join(",", dto.getStickFormList().get(index).getFileList()));

        });
        stockBehaviourStickyDao.saveAll(stockBehaviourStickyList);
    }

}