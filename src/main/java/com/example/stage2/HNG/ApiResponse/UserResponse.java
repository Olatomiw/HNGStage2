package com.example.stage2.HNG.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
