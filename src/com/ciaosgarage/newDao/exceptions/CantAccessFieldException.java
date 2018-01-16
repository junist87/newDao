package com.ciaosgarage.newDao.exceptions;

public class CantAccessFieldException extends RuntimeException {
    public CantAccessFieldException() {
        super();
    }

    public CantAccessFieldException(String message) {
        super(message);
    }

    public CantAccessFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantAccessFieldException(Throwable cause) {
        super(cause);
    }

    protected CantAccessFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
