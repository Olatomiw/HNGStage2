package com.example.stage2.HNG.Service;

import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Model.User;

public interface UserFieldValidator {

    User fieldValidator(User user) throws InvalidInputException;
}


