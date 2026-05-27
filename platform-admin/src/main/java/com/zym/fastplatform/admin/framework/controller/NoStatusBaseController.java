package com.zym.fastplatform.admin.framework.controller;

import com.zym.fastplatform.framework.entity.BaseDTO;
import com.zym.fastplatform.framework.entity.NoStatusBaseEntity;
import com.zym.fastplatform.framework.service.NoStatusBaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class NoStatusBaseController <S extends NoStatusBaseService<T,VO,DTO>,T extends NoStatusBaseEntity,DTO extends BaseDTO,VO> {
    @Autowired
    protected S service;
}
