package com.zym.fastplatform.common.system.service.impl;

import com.zym.fastplatform.common.common.framework.exception.ZException;
import com.zym.fastplatform.common.common.framework.utils.StringUtils;
import com.zym.fastplatform.common.system.convert.DepartmentConvertMapper;
import com.zym.fastplatform.common.system.dao.SysDepartmentDao;
import com.zym.fastplatform.common.system.dao.SysUserDao;
import com.zym.fastplatform.common.system.entity.SysDepartment;
import com.zym.fastplatform.common.system.entity.SysUser;
import com.zym.fastplatform.common.system.entity.dto.DepartmentDTO;
import com.zym.fastplatform.common.system.entity.vo.DepartmentVO;
import com.zym.fastplatform.common.system.service.SysDepartmentService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {
    @Autowired
    private SysDepartmentDao sysDepartmentDao;
    @Autowired
    private DepartmentConvertMapper departmentConvertMapper;
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public List<DepartmentVO> list(DepartmentDTO departmentDTO) {
        List<SysDepartment> departmentList = sysDepartmentDao.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (departmentDTO.getId() != null){
                predicates.add(criteriaBuilder.equal(root.get("id"), departmentDTO.getId()));
            }
            if(StringUtils.isNotBlank(departmentDTO.getName())){
                predicates.add(criteriaBuilder.like(root.get("name"),"%"+departmentDTO.getName()+"%"));
            }
            if(departmentDTO.getParentId() != null){
                predicates.add(criteriaBuilder.equal(root.get("parentId"), departmentDTO.getParentId()));
            }
            if(departmentDTO.getStatus() != null){
                predicates.add(criteriaBuilder.equal(root.get("status"), departmentDTO.getStatus()));
            }
            return predicates.isEmpty()?criteriaBuilder.conjunction():criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        List<DepartmentVO> departmentVOS = departmentConvertMapper.convertToVOList(departmentList);
        List<DepartmentVO> parentList = departmentVOS.stream().filter(x -> x.getParentId() == null).toList();
        recusive(departmentVOS,parentList);
        return parentList;
    }

    @Override
    public void add(DepartmentDTO departmentDTO) {
        SysDepartment department = departmentConvertMapper.convertToEntity(departmentDTO);
        sysDepartmentDao.save(department);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        List<SysUser> userList = sysUserDao.findByDeptIdIn(ids);
        if (!userList.isEmpty()){
            throw new ZException("部门下有用户，不能删除");
        }
        sysDepartmentDao.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    private void recusive(List<DepartmentVO> list,List<DepartmentVO> parentList){
        for (DepartmentVO parent : parentList) {
            List<DepartmentVO> children = list.stream().filter(x -> Objects.equals(x.getParentId(), parent.getId())).peek(x -> x.setParentName(parent.getName())).toList();
            parent.setChildren(children);
            if(!children.isEmpty()){
                recusive(list,children);

            }
        }
    }
}
