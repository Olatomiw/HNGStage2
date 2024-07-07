package com.example.stage2.HNG.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
}
