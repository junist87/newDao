package com.ciaosgarage.newDao.exceptions;

public class NoExistPrimaryKeyFieldException extends RuntimeException {
    public NoExistPrimaryKeyFieldException() {
        super();
    }

    public NoExistPrimaryKeyFieldException(String message) {
        super(message);
    }

    public NoExistPrimaryKeyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoExistPrimaryKeyFieldException(Throwable cause) {
        super(cause);
    }

    protected NoExistPrimaryKeyFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
