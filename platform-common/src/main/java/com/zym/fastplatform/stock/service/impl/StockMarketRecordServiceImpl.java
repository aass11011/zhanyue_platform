package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.common.util.JpaUtils;
import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.framework.utils.StringUtils;
import com.zym.fastplatform.stock.convert.StockMarketRecordConvertMapper;
import com.zym.fastplatform.stock.dao.StockMarketDataDao;
import com.zym.fastplatform.stock.dao.StockMarketFieldDao;
import com.zym.fastplatform.stock.dao.StockMarketRecordDao;
import com.zym.fastplatform.stock.entity.StockMarketData;
import com.zym.fastplatform.stock.entity.StockMarketField;
import com.zym.fastplatform.stock.entity.StockMarketRecord;
import com.zym.fastplatform.stock.entity.dto.StockMarketRecordDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketRecordVO;
import com.zym.fastplatform.stock.service.StockMarketRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StockMarketRecordServiceImpl extends BaseServiceImpl<StockMarketRecordDao, StockMarketRecord, StockMarketRecordConvertMapper, StockMarketRecordDTO, StockMarketRecordVO> implements StockMarketRecordService {
    @Autowired
    private StockMarketDataDao stockMarketDataDao;
    @Autowired
    private StockMarketFieldDao stockMarketFieldDao;
    @Override
    public List<StockMarketRecordVO> findAll(String sort, StockMarketRecordDTO condition) {
        StockMarketRecord entity = convertMapper.toEntity(condition);
        Specification<StockMarketRecord> spec = buildSpecification(entity);
        if(StringUtils.isEmpty(sort)){
            sort = "createdTime desc";
        }
        List<StockMarketRecord> list = dao.findAll(spec, JpaUtils.parseSort(sort));
        list.forEach(item->{
            List<StockMarketData> dataList = stockMarketDataDao.findByRecordId(item.getId());
            item.setList(dataList);
        });
        List<StockMarketRecordVO> voList = convertMapper.toVOList(list);
        voList.forEach(item->{
            item.getList().forEach(data->{
                StockMarketField field = stockMarketFieldDao.findById(data.getFieldId()).orElse(null);
                data.setFieldName(field.getFieldName());
            });
        });
        return voList;
    }


    @Override
    @Transactional
    public void save(StockMarketRecordDTO dto) {
        StockMarketRecord entity = convertMapper.toEntity(dto);
        dao.save(entity);
        List<StockMarketData> list = entity.getList();
        list = list.stream().peek(item->item.setRecordId(entity.getId())).toList();
        stockMarketDataDao.saveAll(list);
    }

    @Override
    @Transactional
    public void delBatch(Long[] ids) {
        super.delBatch(ids);
        stockMarketDataDao.deleteByRecordIdIn(List.of(ids));
    }
}