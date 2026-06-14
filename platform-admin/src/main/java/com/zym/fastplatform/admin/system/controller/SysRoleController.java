package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.common.system.convert.SysRoleConvertMapper;
import com.zym.fastplatform.common.system.convert.SysUserConvertMapper;
import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.system.entity.SysRole;
import com.zym.fastplatform.common.system.entity.dto.SysRoleDTO;
import com.zym.fastplatform.common.system.entity.vo.SysRoleVO;
import com.zym.fastplatform.common.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sysRole")
@Slf4j
public class SysRoleController {


    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysUserConvertMapper sysUserConvertMapper;
    @Resource
    private SysRoleConvertMapper roleConvertMapper;

    @GetMapping("/{id}")
    Result<SysRole> getById(@PathVariable Long id) {
        return Result.ok(sysRoleService.findById(id));
    }

    @PostMapping("/save")
    Result<Void> save(@RequestBody SysRoleDTO sysRole) {
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    @PostMapping("del")
    Result<Void> del(@RequestBody Long[] ids) {
        sysRoleService.delBatch(ids);
        return Result.ok();
    }
    @GetMapping("list")
    Result<Page<SysRoleVO>> list(@RequestParam(defaultValue = "0",name = "pageNum")Integer page, @RequestParam(defaultValue = "10",name = "pageSize")Integer size, String sort, SysRoleDTO sysRole){
        return Result.ok(sysRoleService.findAll(page, size, sort, sysRole));
    }


    @Operation(summary = "根据用户ID获取角色信息分页列表")
    @GetMapping("getRoleByUserId")
    Result<Page<SysRoleVO>> getRoleByUserId(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize")Integer pageSize,@RequestParam("userId")Long userId){
        Page<SysRole> list = sysRoleService.selectByUserId(pageNum,pageSize,userId);
        Page<SysRoleVO> res = list.map(role-> roleConvertMapper.toVO(role));
        return Result.ok(res);
    }
}