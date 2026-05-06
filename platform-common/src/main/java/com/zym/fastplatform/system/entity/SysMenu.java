package com.zym.fastplatform.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zym.fastplatform.framework.entity.BaseEntity;
import com.zym.fastplatform.system.converter.HideFlagConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sys_menu")
@Getter
@Setter
public class SysMenu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 权限字符串
    */
    @Column
    private String name;
    /**
     * 路径
     */
    @Column
    private String path;
    @Column
    private String redirect;
    @Column
    private String icon;
    @Column
    private Long parentId;
    @Column
    private String component;
    @Column
    private String permKey;
    @Column(name = "is_hide")
    @Convert(converter = HideFlagConverter.class)
    private Boolean hideFlag;
    @Column
    private Integer orderNum;
    @Transient
    private List<SysMenu> children;
    /**
     * 是否一直显示,若为true则始终作为目录展示
     */
    @Column
    @Convert(converter = HideFlagConverter.class)
    private Boolean alwaysShow;
    @Column
    private String title;
    @Column
    @Convert(converter = HideFlagConverter.class)
    private Boolean noCache;
    @Column
    @Convert(converter = HideFlagConverter.class)
    private Boolean breadcrumb;
    @Column
    @Convert(converter = HideFlagConverter.class)
    private Boolean affix;
    @Column
    private String activeMenu;
    @Column
    @Convert(converter = HideFlagConverter.class)
    private Boolean noTagsView;
    @Column
    @Convert(converter = HideFlagConverter.class)
    private Boolean canTo;
    @Column
    private Integer status;
    @OneToMany(mappedBy = "menuId",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<SysPermission> permissions;
    @Column
    private Integer type;
}