package com.zym.fastplatform.framework.repository;

import com.zym.fastplatform.system.dao.SysUserDao;
import com.zym.fastplatform.system.entity.SysUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Resource
    private SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserDao.findByUsernameWithRolesAndPermissions(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在：" + username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(sysRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+sysRole.getName()));
            sysRole.getMenus().forEach(sysPermission -> {
                authorities.add(new SimpleGrantedAuthority(sysPermission.getName()));
            });
        });

        return new CustomUserDetails(user.getId(),user.getUsername(),user.getPassword(),user.getGender(),user.getAge(),user.getIdCard(),
                user.getEmail(),user.getPhone(),user.getNickname(),user.getBirthday(),user.getStatus(),user.getLastLogin(),user.getRoles(),authorities);
    }
}
