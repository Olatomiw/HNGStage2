package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Service.UserFieldValidator;
import org.springframework.stereotype.Service;

@Service
public class UserFieldValidatorImpl implements UserFieldValidator {

    @Override
    public User fieldValidator(User user) throws InvalidInputException {
        if (user == null){
            return null;
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()){
            throw new InvalidInputException("Invalid input", "FirstName");
        }
        if (user.getUserId() == null || user.getUserId().isEmpty()){
            throw  new InvalidInputException("Invalid userID", "UserId");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()){
            throw new InvalidInputException("Invalid LastName", "LastName");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@") || user.getEmail().isEmpty()){
            throw new InvalidInputException("Invalid Email field", "Email");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()){
            throw new InvalidInputException("Invalid Phone Number", "Number");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()){
            throw new InvalidInputException("Invalid Password", "password");
        }
        return user;
    }
}
