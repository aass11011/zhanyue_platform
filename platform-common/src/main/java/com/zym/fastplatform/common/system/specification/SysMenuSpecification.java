package com.zym.fastplatform.common.system.specification;

import com.zym.fastplatform.common.system.entity.SysMenu;
import org.springframework.data.jpa.domain.Specification;

public class SysMenuSpecification {
    public static Specification<SysMenu> getById(Long id){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }
    public static Specification<SysMenu> getByName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }
}
