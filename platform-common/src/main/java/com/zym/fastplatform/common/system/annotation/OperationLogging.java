package com.zym.fastplatform.common.system.annotation;

import com.zym.fastplatform.common.system.enums.OperationType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface OperationLogging {
    String value() default "";

    @AliasFor("value")
    String description() default "";

    OperationType type() default OperationType.OTHER;
}
