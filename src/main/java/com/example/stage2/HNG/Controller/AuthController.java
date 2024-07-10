package com.example.stage2.HNG.Controller;

import com.example.stage2.HNG.ApiResponse.AuthResponse.AuthResponseDto;
import com.example.stage2.HNG.ApiResponse.JwtResponseDto;
import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.ApiResponse.Success.UserResponseData;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Service.CreateNewUser;
import com.example.stage2.HNG.Service.ServiceImpl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private CreateNewUser createNewUser;



    @PostMapping("/auth/register")
    public Response<UserResponseData> createUser(@RequestBody UserDto userDto) throws InvalidInputException, InvalidLoginCredentials {
        return createNewUser.addNewUser(userDto);
    }

    @GetMapping("/auth/login")
    public JwtResponseDto login(@RequestBody AuthResponseDto authResponseDto) throws InvalidLoginCredentials {
        return authService.jwtResponseDto(authResponseDto);

    }

}
