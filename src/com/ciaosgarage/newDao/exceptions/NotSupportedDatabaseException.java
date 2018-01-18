package com.ciaosgarage.newDao.exceptions;

public class NotSupportedDatabaseException extends RuntimeException {
    public NotSupportedDatabaseException() {
        super();
    }

    public NotSupportedDatabaseException(String message) {
        super(message);
    }

    public NotSupportedDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedDatabaseException(Throwable cause) {
        super(cause);
    }

    protected NotSupportedDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
