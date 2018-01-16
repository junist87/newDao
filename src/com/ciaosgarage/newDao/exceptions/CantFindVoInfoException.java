package com.ciaosgarage.newDao.exceptions;

public class CantFindVoInfoException extends RuntimeException {
    public CantFindVoInfoException() {
        super();
    }

    public CantFindVoInfoException(String message) {
        super(message);
    }

    public CantFindVoInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantFindVoInfoException(Throwable cause) {
        super(cause);
    }

    protected CantFindVoInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
