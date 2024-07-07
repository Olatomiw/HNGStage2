package com.example.stage2.HNG.Controller;

import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.ApiResponse.Success.UserResponse;
import com.example.stage2.HNG.ApiResponse.Success.UserResponseData;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Service.CreateNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private CreateNewUser createNewUser;

    @PostMapping("/auth/register")
    public Response<UserResponseData> createUser(@RequestBody  UserDto userDto) throws InvalidInputException {
        return createNewUser.addNewUser(userDto);
    }
}
