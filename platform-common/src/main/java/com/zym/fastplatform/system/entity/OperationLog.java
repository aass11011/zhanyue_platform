package com.zym.fastplatform.system.entity;

import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "sys_oper_log")
@Entity
public class OperationLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private String ip;

    @Column
    private String method;

    @Column
    private Integer operationType;

    @Column
    private String params;

    @Column
    private String result;
    /**
     * 操作时间
     */
    @Column
    private LocalDateTime time;
    /**
     * 耗时
     */
    @Column
    private Long duration;
    @Column
    private String username;
    @Column(name = "is_deleted")
    private Integer deletedFlag;
}
