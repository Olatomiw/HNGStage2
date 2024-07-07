package com.example.stage2.HNG.Mapper;

import com.example.stage2.HNG.ApiResponse.UserResponse;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {



    public static User mapToUser(UserDto userDto, PasswordEncoder passwordEncoder){
        User orgUser = new User(

                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getPhone()
        );
        return orgUser;
    }

    public static UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
        return userResponse;
    }
}
