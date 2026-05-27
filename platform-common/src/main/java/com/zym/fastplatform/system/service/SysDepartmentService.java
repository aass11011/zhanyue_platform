package com.zym.fastplatform.system.service;

import com.zym.fastplatform.system.entity.dto.DepartmentDTO;
import com.zym.fastplatform.system.entity.vo.DepartmentVO;

import java.util.List;

public interface SysDepartmentService {
    List<DepartmentVO> list(DepartmentDTO departmentDTO);

    void add(DepartmentDTO departmentDTO);

    void deleteBatch(Long[] ids);
}
