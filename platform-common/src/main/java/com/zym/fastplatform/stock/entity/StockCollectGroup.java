package com.zym.fastplatform.stock.entity;

import com.zym.fastplatform.framework.entity.BaseEntity;
import com.zym.fastplatform.framework.entity.NoStatusBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock_collect_group")
public class StockCollectGroup extends NoStatusBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Size(max = 255)
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "is_default")
    private Boolean isDefault;

}