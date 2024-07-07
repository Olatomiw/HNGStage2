package com.example.stage2.HNG.Exception;

import lombok.Data;

import javax.xml.transform.sax.SAXResult;
@Data
public class ExceptionResponse {
    private String field;
    private String message;
    private String status;

    public ExceptionResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
