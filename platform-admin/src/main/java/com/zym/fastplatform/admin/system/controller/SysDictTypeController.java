package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.system.entity.dto.SysDictTypeDTO;
import com.zym.fastplatform.system.entity.vo.SysDictTypeVO;
import com.zym.fastplatform.system.service.SysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysDictType")
@Slf4j
public class SysDictTypeController {
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @GetMapping("/list")
    public Result<Page<SysDictTypeVO>> list(@RequestParam(defaultValue = "0",name = "pageNum")Integer page, @RequestParam(defaultValue = "10",name = "pageSize")Integer size, String sort, SysDictTypeDTO condition) {
        return Result.ok(sysDictTypeService.findAll(page, size, sort, condition));
    }

    @GetMapping("/{id}")
    Result<SysDictTypeVO> getById(@PathVariable("id") String id) {
        return Result.ok(sysDictTypeService.findById(id));
    }



    @PostMapping("save")
    public Result<Void> save(@RequestBody SysDictTypeDTO sysDictType) {
        sysDictTypeService.save(sysDictType);
        return Result.ok();
    }


    @PostMapping("del")
    public Result<Void> delete(@RequestBody String[] ids) {
        sysDictTypeService.delBatch(ids);
        return Result.ok();
    }
}
