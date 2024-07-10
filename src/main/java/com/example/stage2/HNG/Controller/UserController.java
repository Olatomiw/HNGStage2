package com.example.stage2.HNG.Controller;

import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.ApiResponse.Success.UserResponse;
import com.example.stage2.HNG.ApiResponse.Success.UserResponseData;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.UserRepository;
import com.example.stage2.HNG.Service.CreateNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateNewUser createNewUser;



    @GetMapping("/api/organisations")
    public Response<Set<Organization>> getCurrentLoggedInUserOrganization() throws InvalidLoginCredentials {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String s = "LoggedIn User " + userDetails.getUsername();
        Optional<User> byEmail = userRepository.findByEmail(userDetails.getUsername());
        if (byEmail.isEmpty()){
            throw new InvalidLoginCredentials("Bad Credentials", HttpStatusCode.valueOf(404));
    }
        User user = byEmail.get();
        Set<Organization> organizations = user.getOrganizations();
        Response<Set<Organization>> response = new Response<>(
                "Success",
                "Successfully fetched " + user.getFirstName() + " Organizations",
                organizations
        );
        return response;
    }

    @GetMapping("api/users/{id}")
    public ResponseEntity<?> getUser (@PathVariable String id) throws InvalidLoginCredentials {
       return createNewUser.getUserRecord(id);
    }

}
