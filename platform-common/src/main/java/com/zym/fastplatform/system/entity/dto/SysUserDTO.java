package com.zym.fastplatform.system.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SysUserDTO extends BaseDTO {
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
    private String salt;
    private Long[] roles;
    private Long deptId;
}
