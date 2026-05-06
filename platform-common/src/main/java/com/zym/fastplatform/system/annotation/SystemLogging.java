package com.zym.fastplatform.system.annotation;

import com.zym.fastplatform.system.enums.SystemLoggingLevel;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface SystemLogging {
    SystemLoggingLevel level() default SystemLoggingLevel.INFO;

    boolean logParams() default  true;

    boolean logResult() default true;

    boolean logException() default true;
}
