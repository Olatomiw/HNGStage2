package com.example.stage2.HNG.Exception.ExceptionHandler;

import com.example.stage2.HNG.ApiResponse.MyErrorResponse;
import com.example.stage2.HNG.Exception.ExceptionResponses.ErrorResponse;
import com.example.stage2.HNG.Exception.ExceptionResponses.ExceptionResponse;
import com.example.stage2.HNG.Exception.ExceptionResponses.GeneralExceptionResponse;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Exception.UserAlreadyBelongToOrgException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ComponentScan
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> invalidInputExceptionHandler(InvalidInputException invalidInputException){
        ErrorResponse errorResponse = new ErrorResponse(invalidInputException.getErrorResponses());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(InvalidLoginCredentials.class)
    public ResponseEntity<GeneralExceptionResponse> generalExceptionResponseResponseEntity(
            InvalidLoginCredentials invalidLoginCredentials){
        GeneralExceptionResponse generalExceptionResponse = new GeneralExceptionResponse(
                invalidLoginCredentials.getMessage(),
                "Authentication failed",
                invalidLoginCredentials.getStatusCode()
        );
        return new ResponseEntity<>(generalExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyBelongToOrgException.class)
    public ResponseEntity<?> alreadyBelongToOrg(UserAlreadyBelongToOrgException userAlreadyBelongToOrgException){
        return new ResponseEntity<>(userAlreadyBelongToOrgException.getMessage(), HttpStatus.FORBIDDEN);
    }

}
