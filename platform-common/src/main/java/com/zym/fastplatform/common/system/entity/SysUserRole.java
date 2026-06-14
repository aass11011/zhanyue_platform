package com.zym.fastplatform.common.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserRole {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 用户ID
    */
    @Column(name = "user_id")
    private Long userId;

    /**
    * 角色ID
    */
    @Column(name = "role_id")
    private Long roleId;


}
