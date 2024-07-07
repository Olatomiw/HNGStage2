package com.example.stage2.HNG.Exception;

import lombok.Data;

@Data

public class InvalidInputException extends RuntimeException{
    private String field;
    public InvalidInputException (String message, String field){
        super(message);
        this.field = field;
    }
}
