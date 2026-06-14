package com.zym.fastplatform.common.system.entity;

import com.zym.fastplatform.common.common.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "sys_dict_type")
@Getter
@Setter
public class SysDictType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @FuzzyQuery
    private String dictType;
    private Integer sortOrder;
    private String description;
}
