package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.system.entity.dto.DepartmentDTO;
import com.zym.fastplatform.common.system.entity.vo.DepartmentVO;
import com.zym.fastplatform.common.system.service.SysDepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class SysDepartmentController {
    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Operation(summary = "部门列表")
    @GetMapping("list")
    public Result<List<DepartmentVO>> list(DepartmentDTO departmentDTO){
        List<DepartmentVO> res = sysDepartmentService.list(departmentDTO);
        return Result.ok(res);
    }

    @Operation(summary = "添加部门")
    @PostMapping("save")
    public Result<Void> save(@RequestBody DepartmentDTO departmentDTO){
        sysDepartmentService.add(departmentDTO);
        return Result.ok();
    }
    @Operation(summary = "删除部门")
    @PostMapping("delete")
    public Result<Void> delete(@RequestBody Long[] ids){
        sysDepartmentService.deleteBatch(ids);
        return Result.ok();
    }
}

