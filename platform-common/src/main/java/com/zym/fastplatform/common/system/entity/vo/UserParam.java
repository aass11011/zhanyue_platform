package com.zym.fastplatform.common.system.entity.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserParam {
    private String username;
    private Integer gender;
    private String idCard;
    private String email;
    private String phone;
    private LocalDate birthday;
    private Integer status;
    private String remark;
    private Long roleId;
}
