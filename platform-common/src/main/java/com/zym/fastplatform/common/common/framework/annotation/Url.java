package com.zym.fastplatform.common.common.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {
    //该字段是否是完全url，否代表该字段是富文本类型，里面包含url
    boolean complete() default true;

    boolean collection() default false;
}
