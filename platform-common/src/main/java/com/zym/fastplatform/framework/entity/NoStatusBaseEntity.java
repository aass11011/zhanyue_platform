package com.zym.fastplatform.framework.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoStatusBaseEntity{
    @ExcelIgnore
    private String createdBy;
    @Column(name = "created_time",updatable = false,insertable = false)
    @CreatedDate
    @ExcelIgnore
    private LocalDateTime createdTime;
}
