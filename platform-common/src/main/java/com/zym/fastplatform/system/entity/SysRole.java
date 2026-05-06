package com.zym.fastplatform.system.entity;

import com.zym.fastplatform.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
@Getter
@Setter
public class SysRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 名称
    */
    @Column
    @FuzzyQuery
    private String name;

    /**
    * 代表权限的字符串
    */
    @Column
    private String roleKey;

    /**
    * 排序
    */
    @Column
    private Integer orderNum;

    private Integer status;

    @ManyToMany
    @JoinTable(
            name = "sys_role_menu",
            joinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id",referencedColumnName = "id")
    )
    private Set<SysMenu> menus = new HashSet<>();
}