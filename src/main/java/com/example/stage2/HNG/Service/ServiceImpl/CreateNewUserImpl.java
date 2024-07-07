package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.ApiResponse.UserResponse;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Mapper.UserMapper;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.UserRepository;
import com.example.stage2.HNG.Service.CreateNewUser;
import com.example.stage2.HNG.Service.UserFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateNewUserImpl implements CreateNewUser {

    @Autowired
    private UserFieldValidator userFieldValidator;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserResponse addNewUser(UserDto userDto) throws InvalidInputException {
        User user = UserMapper.mapToUser(userDto,passwordEncoder);
        Long count = userRepository.count();
        Long id = ++count;

        user.setUserId(String.format("Id/%04d",id));
        User user1 = userFieldValidator.fieldValidator(user);
        User save = userRepository.save(user1);
        UserResponse userResponse = UserMapper.mapToUserResponse(save);
        return userResponse;
    }
}
