package com.zym.fastplatform.framework.exception;

import com.zym.fastplatform.framework.enums.ErrorCode;

public class ZException extends RuntimeException{
    private int code = 500;
    public ZException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public ZException() {
    }

    public ZException(String message) {
        super(message);
    }

    public ZException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZException(Throwable cause) {
        super(cause);
    }

    public ZException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }
}
