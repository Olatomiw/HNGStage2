package com.example.stage2.HNG.ApiResponse;

import com.example.stage2.HNG.Exception.ExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyErrorResponse {

    private ExceptionResponse exceptionResponse;
}
