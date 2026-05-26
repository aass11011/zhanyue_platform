package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.common.util.SecurityUtils;
import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.system.entity.SysUser;
import com.zym.fastplatform.system.entity.dto.PwdDTO;
import com.zym.fastplatform.admin.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class SysProfileController {

    @Resource
    private SysUserService sysUserService;

    @Operation(summary = "获取用户信息")
    @GetMapping
    public Result<SysUser> profile() {
        return Result.ok(SecurityUtils.getLoginUser().get());
    }
    @Operation(summary = "修改密码")
    @PostMapping("updatePwd")
    Result<Void> updatePwd(@RequestBody PwdDTO pwdDTO) {
        sysUserService.updatePwd(pwdDTO);
        return Result.ok();
    }
}
