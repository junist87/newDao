package com.ciaosgarage.newDao.exceptions;

public class CannotDefineDataTypeException extends RuntimeException {
    public CannotDefineDataTypeException() {
        super();
    }

    public CannotDefineDataTypeException(String message) {
        super(message);
    }

    public CannotDefineDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotDefineDataTypeException(Throwable cause) {
        super(cause);
    }

    protected CannotDefineDataTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
