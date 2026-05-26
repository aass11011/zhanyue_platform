package com.zym.fastplatform.common.util;

import com.zym.fastplatform.framework.repository.CustomUserDetails;
import com.zym.fastplatform.system.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SecurityUtils {
    private SecurityUtils(){}

    public static Optional<SysUser> getLoginUser(){
        return Optional.of(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(principal ->convertToSysUser((CustomUserDetails)principal));
    }

    public static String getLoginUsername(){
        return getLoginUser().map(SysUser::getUsername).orElse(null);
    }
    /**
     * 获取当前登录用户的角色标识列表
     * @return 角色标识字符串集合
     */
    public static List<String> getLoginUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities() != null) {
            return authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(authority -> authority.startsWith("ROLE_")) // 只获取角色，过滤掉其他权限
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    /**
     * 获取当前登录用户的权限标识列表
     * @return 权限标识字符串集合
     */
    public static List<String> getLoginUserPermissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities() != null) {
            return authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(authority -> !authority.startsWith("ROLE_")) // 只获取权限，过滤掉角色
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    /**
     * 判断当前用户是否拥有指定角色
     * @param roleName 角色名称
     * @return 是否拥有该角色
     */
    public static boolean hasRole(String roleName) {
        String role = "ROLE_" + roleName;
        return getLoginUserRoles().contains(role);
    }

    /**
     * 判断当前用户是否拥有指定权限
     * @param permission 权限标识
     * @return 是否拥有该权限
     */
    public static boolean hasPermission(String permission) {
        return getLoginUserPermissions().contains(permission);
    }
    private static SysUser convertToSysUser(CustomUserDetails customUserDetails) {
        // 根据 CustomUserDetails 创建 SysUser 对象
        SysUser sysUser = new SysUser();
        sysUser.setId(customUserDetails.getId()); // 假设 CustomUserDetails 有 getId() 方法
        sysUser.setUsername(customUserDetails.getUsername());
        sysUser.setPassword(customUserDetails.getPassword());
        sysUser.setPhone(customUserDetails.getPhone());
        sysUser.setEmail(customUserDetails.getEmail());
        sysUser.setGender(customUserDetails.getGender());
        sysUser.setAge(customUserDetails.getAge());
        sysUser.setIdCard(customUserDetails.getIdCard());
        sysUser.setNickname(customUserDetails.getNickname());
        sysUser.setBirthday(customUserDetails.getBirthday());
        sysUser.setStatus(customUserDetails.getStatus().byteValue());
        sysUser.setLastLogin(customUserDetails.getLastLogin());
        sysUser.setRoles(customUserDetails.getRoles());
        return sysUser;
    }
}
