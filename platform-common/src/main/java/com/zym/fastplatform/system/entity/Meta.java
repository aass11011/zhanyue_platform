package com.zym.fastplatform.system.entity;

import lombok.Data;

@Data
public class Meta {
    private Boolean hidden;
    private Boolean alwaysShow;
    private String title;
    private String icon;
    private Boolean noCache;
    private Boolean breadcrumb;
    private Boolean affix;
    private String activeMenu;
    private Boolean noTagsView;
    private Boolean canTo;
    private String[] permission;
}
