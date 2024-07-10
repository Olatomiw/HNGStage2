package com.example.stage2.HNG.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddUserToOrgResponse {
    private String status;
    private String message;
}
