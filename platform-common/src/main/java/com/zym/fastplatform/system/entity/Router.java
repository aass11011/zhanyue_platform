package com.zym.fastplatform.system.entity;

import lombok.Data;

import java.util.List;

@Data
public class Router {
    private Long id;
    private Long parentId;
    private String path;
    private String redirect;
    private String name;
    private String component;
    private Meta meta;
    private List<Router> children;
}
