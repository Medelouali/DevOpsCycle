package com.ensa.tests.exceptions;

public class IllegalOperationException extends Exception{
    public IllegalOperationException() {
        super();
    }

    public IllegalOperationException(String message) {
        super(message);
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOperationException(Throwable cause) {
        super(cause);
    }

    protected IllegalOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
