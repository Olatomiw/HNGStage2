package com.example.stage2.HNG.ApiResponse.Success;

import com.example.stage2.HNG.ApiResponse.Success.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseData {
    private String accessToken;
    private UserResponse userResponse;
}
