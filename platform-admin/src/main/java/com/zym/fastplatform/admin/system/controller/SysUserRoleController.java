package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.system.entity.vo.UserRolesVO;
import com.zym.fastplatform.admin.system.service.SysUserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/sysUserRole")
@Slf4j
public class SysUserRoleController {


    @Resource
    private SysUserRoleService sysUserRoleService;

    @GetMapping("addUserRoleBatch")
    Result<Void> addUserRoleBatch(@RequestParam("roleId")Long roleId,@RequestParam("userIds") List<Long> userIds){
        sysUserRoleService.addUserRoleBatch(roleId,userIds);
        return Result.ok();
    }

    @PostMapping("allocateUserRole")
    Result<Void> allocateUserRole(@RequestBody UserRolesVO userRolesVO){
        sysUserRoleService.setUserRoles(userRolesVO);
        return Result.ok();
    }
}