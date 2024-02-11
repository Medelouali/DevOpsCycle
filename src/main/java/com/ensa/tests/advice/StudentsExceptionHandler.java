package com.ensa.tests.advice;

import com.ensa.tests.dtos.Response;
import com.ensa.tests.exceptions.NoSuchStudentException;
import com.ensa.tests.exceptions.StudentAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentsExceptionHandler {
    @ExceptionHandler(value = { NoSuchStudentException.class })
    public ResponseEntity<Response<NoSuchStudentException>> handleNoSuchStudentException(NoSuchStudentException noSuchStudentException){
        return new ResponseEntity<>(new Response<>(
                true,
                noSuchStudentException.getMessage(),
                noSuchStudentException
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = { StudentAlreadyExistsException.class })
    public ResponseEntity<Response<StudentAlreadyExistsException>> handleNoSuchStudentException(StudentAlreadyExistsException studentAlreadyExistsException){
        return new ResponseEntity<>(new Response<>(
                true,
                studentAlreadyExistsException.getMessage(),
                studentAlreadyExistsException
        ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Response<IllegalArgumentException>> handleNoSuchStudentException(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(new Response<>(
                true,
                illegalArgumentException.getMessage(),
                illegalArgumentException
        ),
                HttpStatus.BAD_REQUEST
        );
    }
}
