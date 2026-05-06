package com.zym.fastplatform.common.generator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZField {
    private String name;
    private ZFiledType type;
    private String comment;
}
