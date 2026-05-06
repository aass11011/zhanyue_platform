package com.zym.fastplatform.scripture.service.impl;

import com.zym.fastplatform.common.util.JpaUtils;
import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.scripture.convert.ScriptureConvertMapper;
import com.zym.fastplatform.scripture.dao.ScriptureDao;
import com.zym.fastplatform.scripture.entity.Scripture;
import com.zym.fastplatform.scripture.entity.dto.ScriptureDTO;
import com.zym.fastplatform.scripture.entity.vo.ScriptureVO;
import com.zym.fastplatform.scripture.service.ScriptureService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScriptureServiceImpl extends BaseServiceImpl<ScriptureDao, Scripture, ScriptureConvertMapper, ScriptureDTO, ScriptureVO> implements ScriptureService {


    @Override
    public List<ScriptureVO> listTitle(Integer page, Integer size, String sort, ScriptureDTO condition) {
        Scripture entity = convertMapper.toEntity(condition);
        Specification<Scripture> specification = buildSpecification(entity);
        Sort sortObj = JpaUtils.parseSort(sort);
        PageRequest pageRequest = PageRequest.of(page, size, sortObj);
        List<Scripture> list = dao.findIdAndTitle(specification, pageRequest);
        return convertMapper.toVOList(list);
    }
}