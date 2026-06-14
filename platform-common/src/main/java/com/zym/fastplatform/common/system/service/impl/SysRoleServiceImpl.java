package com.zym.fastplatform.common.system.service.impl;

import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.system.convert.SysRoleConvertMapper;
import com.zym.fastplatform.common.system.dao.SysRoleDao;
import com.zym.fastplatform.common.system.dao.SysRoleMenuDao;
import com.zym.fastplatform.common.system.dao.SysUserDao;
import com.zym.fastplatform.common.system.dao.SysUserRoleDao;
import com.zym.fastplatform.common.system.entity.SysRole;
import com.zym.fastplatform.common.system.entity.SysRoleMenu;
import com.zym.fastplatform.common.system.entity.dto.SysRoleDTO;
import com.zym.fastplatform.common.system.entity.vo.SysRoleVO;
import com.zym.fastplatform.common.system.service.SysRoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRole, SysRoleConvertMapper, SysRoleDTO, SysRoleVO> implements SysRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysRoleConvertMapper sysRoleConvertMapper;


    @Transactional
    public void save(SysRoleDTO entity) {
        SysRole sysRole = sysRoleConvertMapper.toEntity(entity);
        SysRole role = super.save(sysRole);
        sysRoleMenuDao.deleteByRoleId(role.getId());
        for (Long menuId : entity.getMenuIds()) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(role.getId());
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenuDao.save(sysRoleMenu);
        }
    }

    @Override
    public Page<SysRole> selectByUserId(Integer pageNum,Integer pageSize,Long userId) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return dao.selectRoleListByUserId(userId,pageRequest);
    }

    @Override
    @Transactional
    public void delBatch(Long[] ids) {
        super.delBatch(ids);
        sysRoleMenuDao.deleteByRoleIdIn(ids);
    }
}