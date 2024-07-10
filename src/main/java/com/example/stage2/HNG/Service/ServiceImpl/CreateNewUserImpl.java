package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.ApiResponse.AuthResponse.AuthResponseDto;
import com.example.stage2.HNG.ApiResponse.JwtResponseDto;
import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.ApiResponse.Success.UserResponse;
import com.example.stage2.HNG.ApiResponse.Success.UserResponseData;
import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.ExceptionResponses.GeneralExceptionResponse;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Mapper.UserMapper;
import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.OrgRepository;
import com.example.stage2.HNG.Repository.UserRepository;
import com.example.stage2.HNG.Service.CreateNewUser;
import com.example.stage2.HNG.Service.CreateOrganization;
import com.example.stage2.HNG.Service.UserFieldValidator;
import com.example.stage2.HNG.Utils.InfoGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateNewUserImpl implements CreateNewUser {


    @Autowired
    private InfoGetter infoGetter;
    @Autowired
    private UserFieldValidator userFieldValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrgRepository orgRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CreateOrganization createOrganization;
    @Autowired
    private AuthServiceImpl authService;


    @Override
    public Response<UserResponseData> addNewUser(UserDto userDto) throws InvalidInputException, InvalidLoginCredentials {
        User user = UserMapper.mapToUser(userDto);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        Long count = userRepository.count();
        Long id = ++count;
        user.setUserId(String.format("Id-%04d",id));
        User verifiedUser = userFieldValidator.fieldValidator(user);
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()){
            throw new RuntimeException("User Already Exists");
        }
        String userFullName = verifiedUser.getFirstName() + " " + verifiedUser.getLastName();
        OrganizationDto organizationDto = new OrganizationDto(
                verifiedUser.getFirstName()+"'s",
                userFullName+ " organization"
        );
        Organization organization = createOrganization.defaultOrganization(organizationDto);
        organization.getUsers().add(verifiedUser);
//-----------------------------------------------------------Organization ----------------------------------------
        verifiedUser.getOrganizations().add(organization);
        User save = userRepository.save(verifiedUser);
        UserResponse userResponse = UserMapper.mapToUserResponse(save);

        AuthResponseDto authResponseDto =new AuthResponseDto(
                save.getEmail(),
                password
        );
//        Login part
        JwtResponseDto jwtResponseDto = authService.jwtResponseDto(authResponseDto);
        String token = jwtResponseDto.getToken();
        orgRepository.save(organization);
        UserResponseData userResponseData = new UserResponseData(token,userResponse);
        Response<UserResponseData> response = new Response<>("Success", "You have registered Successfully",userResponseData);
        return response;
    }

    @Override
    public ResponseEntity<?> getUserRecord(String id) throws InvalidLoginCredentials {
        String emailOfLoggedInUser = infoGetter.getEmailOfLoggedInUser();
        User loggedInUser = infoGetter.getUserByEmail(emailOfLoggedInUser);
        User userByUserId = infoGetter.getUserByUserId(id);

        if (!loggedInUser.getUserId().equals(id)&&!loggedInUser.getOrganizations().containsAll(userByUserId.getOrganizations())){
            return new ResponseEntity<>(new GeneralExceptionResponse(
                    "Unauthorized",
                    "You don't belong to the organization",
                    HttpStatusCode.valueOf(401)),HttpStatus.UNAUTHORIZED);
        }
        UserResponse userResponseData = UserMapper.mapToUserResponse(userByUserId);

        return new ResponseEntity<>(new Response<UserResponse>(
                "Success","Profile for " + userByUserId.getFirstName() +" fetched", userResponseData ), HttpStatus.OK);
    }


}
