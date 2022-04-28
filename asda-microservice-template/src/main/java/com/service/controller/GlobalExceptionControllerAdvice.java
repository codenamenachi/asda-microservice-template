package com.service.controller;

import com.service.enums.ResponseStatus;
import com.service.exception.AmbiguousEmpIdException;
import com.service.exception.EmployeeNotFoundException;
import com.service.model.ErrorDetails;
import com.service.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundException(EmployeeNotFoundException exception) {
        ErrorDetails errorDetails = ErrorDetails.builder().errorCode("xxx-404").errorMessage(exception.getMessage()).build();
        Response response = Response.builder().status(ResponseStatus.FAILURE).data(errorDetails).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AmbiguousEmpIdException.class)
    public ResponseEntity<Object> ambiguousEmpIdException(Exception exception) {
        ErrorDetails errorDetails = ErrorDetails.builder().errorCode("xxx-400").errorMessage(exception.getMessage()).build();
        Response response = Response.builder().status(ResponseStatus.FAILURE).data(errorDetails).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        ErrorDetails errorDetails = ErrorDetails.builder().errorCode("xxx-500").errorMessage(exception.getMessage()).build();
        Response response = Response.builder().status(ResponseStatus.FAILURE).data(errorDetails).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
