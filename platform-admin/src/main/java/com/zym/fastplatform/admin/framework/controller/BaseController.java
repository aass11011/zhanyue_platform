package com.zym.fastplatform.admin.framework.controller;

import com.zym.fastplatform.common.common.util.SecurityUtils;
import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.common.framework.service.BaseService;
import com.zym.fastplatform.common.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BaseController <S extends BaseService<T,VO,DTO>,T extends BaseEntity,DTO extends BaseDTO,VO> {

    @Autowired
    protected S service;

    SysUser getUser() {
        SysUser user = SecurityUtils.getLoginUser().orElse(null);
        return user;
    }

    @GetMapping("list")
    public Result<Page<VO>> list(String sort, DTO condition) {
        return Result.ok(service.find(sort, condition));
    }

    @GetMapping("listAll")
    public Result<List<VO>> listAll(String sort, DTO condition){
        return Result.ok(service.findAll(sort, condition));
    }

    @PostMapping("save")
    public Result<Void> save(@RequestBody DTO dto) {
        service.save(dto);
        return Result.ok();
    }

    @PostMapping("del")
    public Result<Void> del(@RequestBody Long[] ids) {
        service.delBatch(ids);
        return Result.ok();
    }

    @GetMapping("findById")
    public Result<VO> findById(@RequestParam(value = "id",required = true) Long id) {
        return Result.ok(service.findVOById(id));
    }
}
