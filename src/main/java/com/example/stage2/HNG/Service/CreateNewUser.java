package com.example.stage2.HNG.Service;

import com.example.stage2.HNG.ApiResponse.UserResponse;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.InvalidInputException;
import org.springframework.http.ResponseEntity;

public interface CreateNewUser {

    UserResponse addNewUser(UserDto userDto) throws InvalidInputException;
}
