package com.example.stage2.HNG.ApiResponse.Success;

import com.example.stage2.HNG.Model.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<Organization> listOfOrg;
}
