package com.zym.fastplatform.framework.repository;

import com.zym.fastplatform.system.entity.SysRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Integer gender;
    private Integer age;
    private String idCard;
    private String email;
    private String phone;
    private String nickname;
    private LocalDate birthday;
    private LocalDateTime lastLogin;
    private Set<SysRole> roles;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    //账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //凭证（密码）是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //账户是否启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
