package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.system.constant.UserConstant;
import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.system.entity.SysUser;
import com.zym.fastplatform.system.entity.dto.SysUserDTO;
import com.zym.fastplatform.system.entity.vo.SysUserVO;
import com.zym.fastplatform.system.service.SysUserService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @GetMapping("list")
    Result<Page<SysUserVO>> list(@RequestParam(defaultValue = "0",name = "pageNum")Integer page, @RequestParam(defaultValue = "10",name = "pageSize")Integer size, String sort, SysUserDTO user){
        return Result.ok(sysUserService.findAll(page,size,sort,user));
    }

    @GetMapping("{id}")
    Result<SysUser> getById(@PathVariable("id") Long id) {
        log.info("查询用户信息,id:{}",id);
        return Result.ok(sysUserService.findById(id));
    }

    @PostMapping("save")
    Result<Void> save(@RequestBody SysUserDTO user) {
        user.setPassword(UserConstant.DEFAULT_PASSWORD);
        sysUserService.add(user);
        return Result.ok();
    }

    @PostMapping("/update")
    Result<Void> update(@RequestBody SysUserDTO user) {
        sysUserService.save(user);
        return Result.ok();
    }

    @PostMapping("del")
    Result<Void> deleteById(@RequestBody Long[] ids) {
        sysUserService.delBatch(ids);
        return Result.ok();
    }

}
