package com.example.stage2.HNG.Service;

import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.ApiResponse.Success.UserResponse;
import com.example.stage2.HNG.ApiResponse.Success.UserResponseData;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.InvalidInputException;

public interface CreateNewUser {

    Response<UserResponseData> addNewUser(UserDto userDto) throws InvalidInputException;
}
