package com.ensa.tests.exceptions;

public class NoSuchStudentException extends Exception{
    public NoSuchStudentException() {
        super();
    }

    public NoSuchStudentException(String message) {
        super(message);
    }

    public NoSuchStudentException(String message, Throwable cause) {
        super(message, cause);
    }
}
