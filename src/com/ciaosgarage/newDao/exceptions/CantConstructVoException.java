package com.ciaosgarage.newDao.exceptions;

public class CantConstructVoException extends RuntimeException {
    public CantConstructVoException() {
        super();
    }

    public CantConstructVoException(String message) {
        super(message);
    }

    public CantConstructVoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantConstructVoException(Throwable cause) {
        super(cause);
    }

    protected CantConstructVoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
