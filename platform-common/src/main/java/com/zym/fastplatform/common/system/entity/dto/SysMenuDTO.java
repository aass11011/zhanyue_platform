package com.zym.fastplatform.common.system.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SysMenuDTO {
    private Long id;
    private Integer parentId;
    private String component;
    private String name;
    private String path;
    private List<SysPermissionDTO> permissions;
    private Integer status;
    private Integer type;
    private Boolean hideFlag;
    private Boolean alwaysShow;
    private String title;
    private String icon;
    private Boolean noCache;
    private Boolean breadcrumb;
    private Boolean affix;
    private String activeMenu;
    private Boolean noTagsView;
    private Boolean canTo;
}
