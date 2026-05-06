package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockSseFundsConvertMapper;
import com.zym.fastplatform.stock.dao.StockSseFundsDao;
import com.zym.fastplatform.stock.entity.StockSseFunds;
import com.zym.fastplatform.stock.entity.dto.StockSseFundsDTO;
import com.zym.fastplatform.stock.entity.vo.StockSseFundsVO;
import com.zym.fastplatform.stock.service.StockSseFundsService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockSseFundsServiceImpl extends BaseServiceImpl<StockSseFundsDao, StockSseFunds, StockSseFundsConvertMapper, StockSseFundsDTO, StockSseFundsVO> implements StockSseFundsService {

    @Override
    public List<StockSseFundsVO> findAll(String sort, StockSseFundsDTO condition) {
        Specification<StockSseFunds> spec = (root, query, cb)->{
            Predicate predicate = cb.conjunction();
            if(condition.getStartDate()!=null){
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("statDate"), condition.getStartDate()));
            }
            if(condition.getEndDate()!=null){
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("statDate"), condition.getEndDate()));
            }
            return predicate;
        };
        List<StockSseFunds> list = dao.findAll(spec);
        return convertMapper.toVOList(list);
    }
}