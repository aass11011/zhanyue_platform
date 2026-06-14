package com.zym.fastplatform.common.common.framework.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @ExcelIgnore
    private String createdBy;
    @Column(name = "created_time",updatable = false,insertable = false)
    @CreatedDate
    @ExcelIgnore
    private LocalDateTime createdTime;
    @ExcelIgnore
    private String updatedBy;
    @Column(name = "updated_time",updatable = false,insertable = false)
    @ExcelIgnore
    private LocalDateTime updatedTime;
    private String remark;
}
