package com.example.stage2.HNG.Exception;

import lombok.Data;
import org.springframework.http.HttpStatusCode;
@Data
public class InvalidLoginCredentials extends Throwable{
    private HttpStatusCode statusCode;

    public InvalidLoginCredentials(String message, HttpStatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
