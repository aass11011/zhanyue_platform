package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockMarketFieldConvertMapper;
import com.zym.fastplatform.stock.convert.StockMarketSchemaConvertMapper;
import com.zym.fastplatform.stock.dao.StockMarketFieldDao;
import com.zym.fastplatform.stock.dao.StockMarketSchemaDao;
import com.zym.fastplatform.stock.entity.StockMarketField;
import com.zym.fastplatform.stock.entity.StockMarketSchema;
import com.zym.fastplatform.stock.entity.dto.StockMarketSchemaDTO;
import com.zym.fastplatform.stock.entity.vo.StockMarketSchemaVO;
import com.zym.fastplatform.stock.service.StockMarketSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StockMarketSchemaServiceImpl extends BaseServiceImpl<StockMarketSchemaDao, StockMarketSchema, StockMarketSchemaConvertMapper, StockMarketSchemaDTO, StockMarketSchemaVO> implements StockMarketSchemaService {

    @Autowired
    private StockMarketFieldConvertMapper fieldConvertMapper;
    @Autowired
    private StockMarketFieldDao fieldDao;

    @Override
    @Transactional
    public void save(StockMarketSchemaDTO dto) {
        StockMarketSchema entity = convertMapper.toEntity(dto);
        dao.save(entity);
        List<StockMarketField> fieldList = fieldConvertMapper.toEntityList(dto.getFields());
        fieldList.forEach(field -> {
            field.setSchemaId(entity.getId());
            if(field.getFieldOptions()==null || field.getFieldOptions().isEmpty()){
                field.setFieldOptions("{}");
            }
        });
        fieldDao.saveAll(fieldList);
    }
}