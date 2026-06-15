package com.zym.fastplatform.portal.framework.controller;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.common.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BaseController<S extends BaseService<T,VO,DTO>,T extends BaseEntity,DTO extends BaseDTO,VO> {

    @Autowired
    protected S service;


    @GetMapping("list")
    public Result<Page<VO>> list(String sort, DTO condition) {
        return Result.ok(service.find(sort, condition));
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
