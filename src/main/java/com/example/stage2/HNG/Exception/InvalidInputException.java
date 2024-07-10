package com.example.stage2.HNG.Exception;

import com.example.stage2.HNG.ApiResponse.MyErrorResponse;
import com.example.stage2.HNG.Exception.ExceptionResponses.ExceptionResponse;
import lombok.Data;

import java.util.List;

@Data

public class InvalidInputException extends RuntimeException{
    private List<ExceptionResponse> errorResponses;

    public InvalidInputException(List<ExceptionResponse> errorResponses) {
        this.errorResponses = errorResponses;
    }
}
