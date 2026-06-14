package com.zym.fastplatform.common.system.service;

import com.zym.fastplatform.common.system.entity.dto.DepartmentDTO;
import com.zym.fastplatform.common.system.entity.vo.DepartmentVO;

import java.util.List;

public interface SysDepartmentService {
    List<DepartmentVO> list(DepartmentDTO departmentDTO);

    void add(DepartmentDTO departmentDTO);

    void deleteBatch(Long[] ids);
}
