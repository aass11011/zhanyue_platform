package com.zym.fastplatform.system.entity;

import com.zym.fastplatform.framework.annotation.FuzzyQuery;
import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "sys_dict_data")
@Getter
@Setter
public class SysDictData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String typeId;
    @FuzzyQuery
    private String dictType;
    private Integer sortOrder;
    @FuzzyQuery
    private String dictName;
    private String dictValue;
    private Integer status;
}
