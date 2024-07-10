package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.Exception.ExceptionResponses.ExceptionResponse;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Service.UserFieldValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFieldValidatorImpl implements UserFieldValidator {

    @Override
    public User fieldValidator(User user) throws InvalidInputException {

        List<ExceptionResponse> exceptionResponses = new ArrayList<>();

        if (user == null){
            return null;
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()){
            exceptionResponses.add(new ExceptionResponse("Invalid input", "Firstname"));
        }
        if (user.getUserId() == null || user.getUserId().isEmpty()){
            exceptionResponses.add(new ExceptionResponse("Invalid userID", "UserId"));
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()){
            exceptionResponses.add(new ExceptionResponse("Invalid LastName", "LastName"));
        }
        if (user.getEmail() == null || !user.getEmail().contains("@") || user.getEmail().isEmpty()){
            exceptionResponses.add(new ExceptionResponse("Invalid Email field", "Email"));
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()){
            exceptionResponses.add(new ExceptionResponse("Invalid Phone Number", "Number"));
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()){
            exceptionResponses.add(new ExceptionResponse("Invalid Password", "password"));
        }
        if (!exceptionResponses.isEmpty()){

        }
        return user;
    }
}
