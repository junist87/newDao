package com.ciaosgarage.newDao.exceptions;

public class CantCloneColumnInstance extends RuntimeException {
    public CantCloneColumnInstance() {
        super();
    }

    public CantCloneColumnInstance(String message) {
        super(message);
    }

    public CantCloneColumnInstance(String message, Throwable cause) {
        super(message, cause);
    }

    public CantCloneColumnInstance(Throwable cause) {
        super(cause);
    }

    protected CantCloneColumnInstance(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
