package com.ciaosgarage.newDao.exceptions;

public class NoValueExcption extends RuntimeException {
    public NoValueExcption() {
        super();
    }

    public NoValueExcption(String message) {
        super(message);
    }

    public NoValueExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public NoValueExcption(Throwable cause) {
        super(cause);
    }

    protected NoValueExcption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
