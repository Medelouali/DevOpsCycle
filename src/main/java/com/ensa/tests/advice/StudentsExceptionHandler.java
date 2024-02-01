package com.ensa.tests.advice;

import com.ensa.tests.exceptions.NoSuchStudentException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentsExceptionHandler {
    @ExceptionHandler(value = { NoSuchStudentException.class })
    public String handleNoSuchStudentException(NoSuchStudentException noSuchStudentException){
        return noSuchStudentException.getMessage();
    }
}
