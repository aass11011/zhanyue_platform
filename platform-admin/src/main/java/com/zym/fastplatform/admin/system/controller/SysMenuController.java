package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.system.entity.Router;
import com.zym.fastplatform.system.entity.SysMenu;
import com.zym.fastplatform.system.entity.dto.SysMenuDTO;
import com.zym.fastplatform.system.entity.vo.SysMenuVO;
import com.zym.fastplatform.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/sysMenu")
@Slf4j
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("/save")
    Result<Void> save(@RequestBody SysMenuDTO sysMenuDTO) {
        sysMenuService.save(sysMenuDTO);
        return Result.ok();
    }

    /**
     * @author: zhangym
     * @data: 2024/12/22 2:00
     * @descrption: 页面列表
     **/
    @GetMapping("/list")
    Result<List<SysMenuVO>> list(SysMenu param){
        List<SysMenuVO> res = sysMenuService.selectList(param);
        return Result.ok(res);
    }
    @PostMapping("/delete")
    Result<Void> delete(@RequestBody Long[] ids){
        sysMenuService.deleteBatch(ids);
        return Result.ok();
    }
    @Operation(summary = "获取角色权限")
    @GetMapping("getRouters")
    Result<List<Router>> getRouters(){
        List<Router> res = sysMenuService.selectRoutes();
        return Result.ok(res);
    }
    @Operation(summary = "获取权限列表",description = "根据当前用户权限获取路由")
    @GetMapping("getPermTree")
    public Result<List<Router>> getPermTree(){
        List<Router> res = sysMenuService.getPermTree();
        return Result.ok(res);
    }
}