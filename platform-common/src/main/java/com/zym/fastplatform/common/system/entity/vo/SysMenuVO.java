package com.zym.fastplatform.common.system.entity.vo;

import com.zym.fastplatform.common.system.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SysMenuVO {
    private Long id;
    private Long parentId;

    private String name;

    private String path;

    private String permType;
    
    private String icon;
    
    private String component;
    
    private String permKey;

    private Boolean hideFlag;
    
    private Integer orderNum;

    private List<SysMenuVO> children;

    private Boolean alwaysShow;
    
    private String title;

    private Boolean noCache;

    private Boolean breadcrumb;
    

    private Boolean affix;
    
    private String activeMenu;

    private Boolean noTagsView;

    private Boolean canTo;

    private Integer deletedFlag;

    private List<SysPermission> permissions;

    private String parentName;

    private Integer status;

    private Integer type;
}
