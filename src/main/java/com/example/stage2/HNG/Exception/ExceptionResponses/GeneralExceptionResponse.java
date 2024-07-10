package com.example.stage2.HNG.Exception.ExceptionResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralExceptionResponse {
    private String status;
    private String message;
    private HttpStatusCode statusCode;
}
