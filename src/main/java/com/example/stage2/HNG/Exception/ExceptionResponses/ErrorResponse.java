package com.example.stage2.HNG.Exception.ExceptionResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private List<ExceptionResponse> errors;
}
