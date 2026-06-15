package com.zym.fastplatform.common.system.service.impl;

import com.zym.fastplatform.common.system.entity.*;
import com.zym.fastplatform.common.common.util.JpaUtils;
import com.zym.fastplatform.common.common.util.SecurityUtils;
import com.zym.fastplatform.common.common.framework.exception.ZException;
import com.zym.fastplatform.common.common.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.common.system.convert.SysUserConvertMapper;
import com.zym.fastplatform.common.system.dao.SysDepartmentDao;
import com.zym.fastplatform.common.system.dao.SysUserDao;
import com.zym.fastplatform.common.system.dao.SysUserRoleDao;
import com.zym.fastplatform.common.system.entity.*;
import com.zym.fastplatform.common.system.entity.dto.PwdDTO;
import com.zym.fastplatform.common.system.entity.dto.SysUserDTO;
import com.zym.fastplatform.common.system.entity.vo.SysUserVO;
import com.zym.fastplatform.common.system.service.SysUserService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUser, SysUserConvertMapper, SysUserDTO, SysUserVO> implements SysUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    private SysUserConvertMapper sysUserConvertMapper;

    @Resource
    private SysDepartmentDao sysDepartmentDao;


    @Override
    public Page<SysUserVO> find(String sort, SysUserDTO condition) {
        SysUser sysUser = convertMapper.toEntity(condition);
        Specification<SysUser> spec = buildSpecification(sysUser);
        Sort sortObj = JpaUtils.parseSort(sort);
        PageRequest pageRequest = PageRequest.of(condition.getPageIndex(), condition.getPageSize(),sortObj);
        if(condition.getDeptId() != null){
            List<SysDepartment> childDeptList = sysDepartmentDao.findAllChildDept(condition.getDeptId());
            List<Long> deptIds = childDeptList.stream().map(SysDepartment::getId).toList();
            spec = spec.and((root, query, cb) -> cb.in(root.get("deptId")).value(deptIds));
        }
        Page<SysUser> sysUsers = dao.findAll(spec, pageRequest);
        List<SysUserVO> voList = new ArrayList<>();
        sysUsers.getContent().forEach(item -> {
            SysUserVO vo = convertMapper.toVO(item);
            vo.setRoles(item.getRoles().stream().map(SysRole::getId).toArray(Long[]::new));
            voList.add(vo);
        });
        Page<SysUserVO> voPage = new PageImpl<>(voList, pageRequest, sysUsers.getTotalElements());
        return voPage;
    }

    @Override
    @Transactional
    public void save(SysUserDTO entity) {
        SysUser sysUser = sysUserConvertMapper.toEntity(entity);
        SysUser user = super.save(sysUser);
        sysUserRoleDao.deleteByUserId(sysUser.getId());
        for (Long roleId : entity.getRoles()) {
            SysUserRole sysUserRole = SysUserRole.builder().userId(user.getId()).roleId(roleId).build();
            sysUserRoleDao.save(sysUserRole);
        }
    }

    @Override
    public void add(SysUserDTO user) {
        user.setPassword(encoder.encode(user.getPassword()));
        save( user);
    }

    @Override
    public void updatePwd(PwdDTO pwdDTO) {
        Long userId = SecurityUtils.getLoginUser().get().getId();
        SysUser sysUser = findById(userId);
        if (!encoder.matches(pwdDTO.getPassword(), sysUser.getPassword())) {
            throw new ZException("旧密码错误");
        }
        sysUser.setPassword(encoder.encode(pwdDTO.getNewPassword()));
        save(sysUser);
    }

    @Override
    public void login(SysUser user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            authenticationManager.authenticate(token);
            SysUser sysUser = dao.findByUsername(user.getUsername());
            if (sysUser != null) {
                sysUser.setLastLogin(LocalDateTime.now());
                dao.save(sysUser);
            }
        } catch (AuthenticationException e) {
            throw new ZException("用户名密码错误",e);
        }
    }

    @Override
    public void registe(RegisteUser registeUser) {
        SysUser user = new SysUser();
        user.setUsername(registeUser.getUsername());
        user.setPassword(registeUser.getPassword());
        save(user);
    }

    @Override
    @Transactional
    public void delBatch(Long[] ids) {
        dao.deleteAllByIdInBatch(List.of(ids));
        sysUserRoleDao.deleteAllByUserIdIn(List.of(ids));
    }
}
