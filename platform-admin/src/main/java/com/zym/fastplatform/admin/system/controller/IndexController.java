package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.system.entity.RegisteUser;
import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.system.entity.SysUser;
import com.zym.fastplatform.system.service.SysUserService;
import com.zym.fastplatform.framework.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {
    @Resource
    private SysUserService sysUserService;

    @PostMapping("login")
    Result<String> login(@RequestBody SysUser user) {
        sysUserService.login(user);
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());
        return Result.ok(token);
    }

    @GetMapping("getCode")
    public Result<String> getCode(@RequestParam("phone")String phone){
        log.info("验证码请求已发送："+phone);
        return Result.ok("1234");
    }

    @PostMapping("registe")
    public Result<Void> registe(@RequestBody RegisteUser registeUser){
        if("1234".equals(registeUser.getCode())){
            sysUserService.registe(registeUser);
            return Result.ok();
        }else {
            return Result.error("验证码错误");
        }
    }
}
