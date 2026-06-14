package com.zym.fastplatform.common.system.service.impl;

import com.zym.fastplatform.common.common.util.SecurityUtils;
import com.zym.fastplatform.common.common.framework.exception.ZException;
import com.zym.fastplatform.common.system.convert.SysMenuConvertMapper;
import com.zym.fastplatform.common.system.dao.SysMenuDao;
import com.zym.fastplatform.common.system.dao.SysPermissionDao;
import com.zym.fastplatform.common.system.entity.Router;
import com.zym.fastplatform.common.system.entity.SysMenu;
import com.zym.fastplatform.common.system.entity.SysRole;
import com.zym.fastplatform.common.system.entity.SysUser;
import com.zym.fastplatform.common.system.entity.dto.SysMenuDTO;
import com.zym.fastplatform.common.system.entity.vo.SysMenuVO;
import com.zym.fastplatform.common.system.service.SysMenuService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysMenuConvertMapper convertMapper;
    @Autowired
    private SysPermissionDao sysPermissionDao;


    @Override
    public List<SysMenuVO> selectList(SysMenu param) {
        List<SysMenu> list = sysMenuDao.findAll(Example.of(param));
        List<SysMenuVO> voList = convertMapper.convertToVO(list);
        List<SysMenuVO> parentList = voList.stream().filter(x -> x.getParentId() == null).toList();
        recursive(parentList,voList);
        return parentList;
    }

    private void recursive(List<SysMenuVO> parentlist, List<SysMenuVO> list) {
        for (SysMenuVO parent : parentlist) {
            List<SysMenuVO> childrens = list.stream().filter(x -> Objects.equals(x.getParentId(), parent.getId()))
                    .peek(x->x.setParentName(parent.getTitle())).toList();
            if(!childrens.isEmpty()){
                recursive(childrens,list);
                parent.setChildren(childrens);
            }
        }
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] ids) {
        sysMenuDao.deleteAllByIdInBatch(Arrays.asList(ids));
        sysPermissionDao.deleteByMenuIdIn(Arrays.asList(ids));
    }


    @Override
    public List<Router> selectRoutes() {
        List<SysMenu> sysMenuList = sysMenuDao.findAll();
        List<Router> routers = convertMapper.convertToRouter(sysMenuList);
        List<Router> parentList = routers.stream().filter(x -> x.getParentId() == null).toList();
        recursive2(parentList,routers);
        return parentList;
    }


    @Override
    public List<Router> getPermTree() {
        SysUser sysUser = SecurityUtils.getLoginUser().get();
        Set<SysRole> roles = sysUser.getRoles();
        Set<String> roleSet = roles.stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
        List<SysMenu> list;
        if(roleSet.contains("admin")){
            list = sysMenuDao.findAll();
        }else {
            Long userId = sysUser.getId();
            list = sysMenuDao.findByUserId(userId);
        }
        List<Router> routers = convertMapper.convertToRouter(list);
        List<Router> parentList = routers.stream().filter(x -> x.getParentId() == null).toList();
        recursive2(parentList,routers);
        return parentList;

    }



    @Override
    @Transactional
    public void save(SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = convertMapper.convertToEntity(sysMenuDTO);
        Optional<SysMenu> exist = sysMenuDao.findOne(((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(sysMenuDTO.getId()!=null){
                predicates.add(criteriaBuilder.notEqual(root.get("id"), sysMenuDTO.getId()));
            }
            predicates.add(criteriaBuilder.equal(root.get("name"), sysMenuDTO.getName()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
        if(exist.isPresent()){
            throw new ZException("菜单名称已存在");
        }
        sysMenuDao.save(sysMenu);
        if(sysMenu.getPermissions() != null&&!sysMenu.getPermissions().isEmpty()){
            if (sysMenuDTO.getId()!=null){
                sysPermissionDao.deleteByMenuId(sysMenuDTO.getId());
            }
            sysMenu.getPermissions().forEach(x->{
                x.setMenuId(sysMenu.getId());
            });
            sysPermissionDao.saveAll(sysMenu.getPermissions());
        }
    }

    private void recursive2(List<Router> parentlist, List<Router> list) {
        for (Router parent : parentlist) {
            List<Router> childrens = list.stream().filter(x -> Objects.equals(x.getParentId(), parent.getId())).toList();
            if(!childrens.isEmpty()){
                recursive2(childrens,list);
                parent.setChildren(childrens);
            }
        }
    }
}