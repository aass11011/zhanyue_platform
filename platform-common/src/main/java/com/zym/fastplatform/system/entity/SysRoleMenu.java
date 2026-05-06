package com.zym.fastplatform.system.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "sys_role_menu")
@Data
public class SysRoleMenu {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 角色ID
    */
    @Column(name = "role_id")
    private Long roleId;

    /**
    * 权限ID
    */
    @Column(name = "menu_id")
    private Long menuId;


}