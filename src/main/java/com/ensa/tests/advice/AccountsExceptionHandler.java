package com.ensa.tests.advice;

import com.ensa.tests.dtos.Response;
import com.ensa.tests.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountsExceptionHandler {
    @ExceptionHandler(value = { NoSuchAccountException.class })
    public ResponseEntity<Response<NoSuchAccountException>> handleNoSuchStudentException(NoSuchAccountException noSuchAccountException){
        return new ResponseEntity<>(new Response<>(
                true,
                noSuchAccountException.getMessage(),
                noSuchAccountException
        ),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(value = { IllegalOperationException.class })
    public ResponseEntity<Response<IllegalOperationException>> handleIllegalOperationException(IllegalOperationException illegalOperationException){
        return new ResponseEntity<>(new Response<>(
                true,
                illegalOperationException.getMessage(),
                illegalOperationException
        ),
                HttpStatus.BAD_REQUEST
        );
    }


}
