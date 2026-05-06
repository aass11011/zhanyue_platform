package com.zym.fastplatform.system.entity.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SysUserVO {
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
    private Integer status;
    private LocalDateTime lastLogin;
    private Long[] roles;
    private Long deptId;
    private LocalDateTime createdTime;
}
