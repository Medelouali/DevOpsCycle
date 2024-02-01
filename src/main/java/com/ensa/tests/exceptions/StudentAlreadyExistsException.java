package com.ensa.tests.exceptions;

public class StudentAlreadyExistsException extends Exception{
    public StudentAlreadyExistsException() {
        super();
    }

    public StudentAlreadyExistsException(String message) {
        super(message);
    }

    public StudentAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
