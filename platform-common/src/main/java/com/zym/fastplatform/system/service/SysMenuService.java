package com.zym.fastplatform.system.service;

import com.zym.fastplatform.system.entity.Router;
import com.zym.fastplatform.system.entity.SysMenu;
import com.zym.fastplatform.system.entity.dto.SysMenuDTO;
import com.zym.fastplatform.system.entity.vo.SysMenuVO;

import java.util.List;

public interface SysMenuService {


    List<SysMenuVO> selectList(SysMenu param);

    void deleteBatch(Long[] ids);

    List<Router> selectRoutes();

    List<Router> getPermTree();

    void save(SysMenuDTO sysMenuDTO);
}