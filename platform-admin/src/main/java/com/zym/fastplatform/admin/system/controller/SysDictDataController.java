package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.system.entity.dto.SysDictDataDTO;
import com.zym.fastplatform.system.entity.vo.SysDictDataVO;
import com.zym.fastplatform.admin.system.service.SysDictDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysDictData")
@Slf4j
public class SysDictDataController {
    @Autowired
    private SysDictDataService sysDictDataService;

    @GetMapping("list")
    public Result<Page<SysDictDataVO>> list(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, String sort, SysDictDataDTO sysDictData) {
        return Result.ok(sysDictDataService.findAll(pageNum,pageSize,sort,sysDictData));
    }

    @GetMapping("listByType")
    public Result<List<SysDictDataVO>> listByType(@RequestParam("typeId") String typeId) {
        return Result.ok(sysDictDataService.listByType(typeId));
    }

    @GetMapping("{id}")
    public Result<SysDictDataVO> getById(@PathVariable String id) {
        return Result.ok(sysDictDataService.findById(id));
    }

    @PostMapping("save")
    public Result<Void> save(@RequestBody SysDictDataDTO sysDictData) {
        sysDictDataService.save(sysDictData);
        return Result.ok();
    }

    @PostMapping("del")
    public Result<Void> delete(@RequestBody String[] ids) {
        sysDictDataService.delBatch(ids);
        return Result.ok();
    }
}
