package com.zym.fastplatform.common.system.entity;


import com.zym.fastplatform.common.common.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "sys_user")
@Data
public class SysUser extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Integer gender;
    private Integer age;
    private String idCard;
    private String email;
    private String phone;
    private LocalDate birthday;
    private LocalDateTime lastLogin;
    private String salt;
    @FuzzyQuery(ignore = true)
    private Long deptId;
    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<SysRole> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
