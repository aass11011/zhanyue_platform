package com.zym.fastplatform.common.system.convert;

import com.zym.fastplatform.common.system.entity.SysDepartment;
import com.zym.fastplatform.common.system.entity.dto.DepartmentDTO;
import com.zym.fastplatform.common.system.entity.vo.DepartmentVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentConvertMapper {
    DepartmentVO convertToVO(SysDepartment department);
    List<DepartmentVO> convertToVOList(List<SysDepartment> departments);

    SysDepartment convertToEntity(DepartmentDTO departmentDTO);
    List<SysDepartment> convertToEntityList(List<DepartmentDTO> departmentDTOS);

}
