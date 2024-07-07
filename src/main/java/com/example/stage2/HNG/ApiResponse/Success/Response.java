package com.example.stage2.HNG.ApiResponse.Success;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String status;
    private String message;
    private T data;

}
