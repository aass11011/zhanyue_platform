package com.zym.fastplatform.portal.system.controller;

import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.system.entity.vo.SysDictDataVO;
import com.zym.fastplatform.common.system.service.SysDictDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysDictData")
@Slf4j
public class SysDictDataController {
    @Autowired
    private SysDictDataService sysDictDataService;

    @GetMapping("listByType")
    public Result<List<SysDictDataVO>> listByType(@RequestParam("typeId") String typeId) {
        return Result.ok(sysDictDataService.listByType(typeId));
    }
}
