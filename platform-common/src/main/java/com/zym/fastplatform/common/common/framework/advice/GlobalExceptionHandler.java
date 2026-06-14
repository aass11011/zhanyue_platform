package com.zym.fastplatform.common.common.framework.advice;

import com.zym.fastplatform.common.common.framework.entity.Result;
import com.zym.fastplatform.common.common.framework.exception.ZException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ZException.class)
    public Result<Void> handleZException(ZException e){
        log.error(e.getMessage(),e);
        return Result.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e){
        log.error(e.getMessage(),e);
        return Result.error(e.getMessage());
    }
}
