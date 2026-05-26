package com.zym.fastplatform.framework.entity;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoStatusBaseEntity extends BaseEntity{
    @Transient
    private Byte status;
}
