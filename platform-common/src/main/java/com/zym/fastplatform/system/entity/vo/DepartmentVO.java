package com.zym.fastplatform.system.entity.vo;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DepartmentVO {
    private Long id;
    private String name;
    private Long parentId;
    private String parentName;
    private Integer status;
    @Transient
    private List<DepartmentVO> children;
    private LocalDateTime createdTime;
    private String remark;
}
