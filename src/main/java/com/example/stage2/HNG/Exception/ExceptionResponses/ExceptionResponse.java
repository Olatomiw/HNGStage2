package com.example.stage2.HNG.Exception.ExceptionResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.sax.SAXResult;
@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String field;
    private String message;

    public ExceptionResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
