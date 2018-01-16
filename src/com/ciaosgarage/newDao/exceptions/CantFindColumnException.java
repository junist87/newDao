package com.ciaosgarage.newDao.exceptions;

public class CantFindColumnException extends RuntimeException {
    public CantFindColumnException() {
        super();
    }

    public CantFindColumnException(String message) {
        super(message);
    }

    public CantFindColumnException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantFindColumnException(Throwable cause) {
        super(cause);
    }

    protected CantFindColumnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
