package com.example.stage2.HNG.Exception.ExceptionHandler;

import com.example.stage2.HNG.ApiResponse.MyErrorResponse;
import com.example.stage2.HNG.Exception.ExceptionResponse;
import com.example.stage2.HNG.Exception.InvalidInputException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ComponentScan
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<MyErrorResponse> invalidInputExceptionHandler(InvalidInputException invalidInputException){
        MyErrorResponse errorResponse = new MyErrorResponse();
        ExceptionResponse exceptionResponse =  new ExceptionResponse(invalidInputException.getMessage(),
                invalidInputException.getField());
       errorResponse.setExceptionResponse(exceptionResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST) ;
    }

}
