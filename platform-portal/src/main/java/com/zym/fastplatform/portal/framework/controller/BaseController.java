package com.zym.fastplatform.portal.framework.controller;

import com.zym.fastplatform.framework.entity.BaseDTO;
import com.zym.fastplatform.framework.entity.BaseEntity;
import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BaseController<S extends BaseService<T,VO,DTO>,T extends BaseEntity,DTO extends BaseDTO,VO> {

    @Autowired
    protected S service;


    @GetMapping("list")
    public Result<Page<VO>> list(@RequestParam(defaultValue = "0", name = "pageNum") Integer page, @RequestParam(defaultValue = "10", name = "pageSize") Integer size, String sort, DTO condition) {
        return Result.ok(service.findAll(page, size, sort, condition));
    }

    @GetMapping("listAll")
    public Result<List<VO>> listAll(String sort, DTO condition){
        return Result.ok(service.findAll(sort, condition));
    }


    @GetMapping("findById")
    public Result<VO> findById(@RequestParam(value = "id",required = true) Long id) {
        return Result.ok(service.findVOById(id));
    }
}
