package com.zym.fastplatform.system.convert;

import com.zym.fastplatform.system.entity.Router;
import com.zym.fastplatform.system.entity.SysMenu;
import com.zym.fastplatform.system.entity.dto.SysMenuDTO;
import com.zym.fastplatform.system.entity.vo.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysMenuConvertMapper {

    @Mappings({
            @Mapping(source = "title",target = "meta.title"),
            @Mapping(source = "icon",target = "meta.icon"),
            @Mapping(source = "hideFlag",target = "meta.hidden"),
            @Mapping(source = "alwaysShow",target = "meta.alwaysShow"),
            @Mapping(source = "noCache",target = "meta.noCache"),
            @Mapping(source = "breadcrumb",target = "meta.breadcrumb"),
            @Mapping(source = "affix",target = "meta.affix"),
            @Mapping(source = "activeMenu",target = "meta.activeMenu"),
            @Mapping(source = "noTagsView",target = "meta.noTagsView"),
            @Mapping(source = "canTo",target = "meta.canTo")
    })
    Router convertToRouter(SysMenu sysMenu);

    List<Router> convertToRouter(List<SysMenu> sysMenus);

    SysMenuVO convertToVO(SysMenu sysMenu);
    List<SysMenuVO> convertToVO(List<SysMenu> sysMenus);

    SysMenu convertToEntity(SysMenuDTO sysMenuDTO);
}
