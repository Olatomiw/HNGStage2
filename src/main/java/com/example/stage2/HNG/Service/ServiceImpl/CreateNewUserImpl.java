package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.ApiResponse.Success.UserResponse;
import com.example.stage2.HNG.ApiResponse.Success.UserResponseData;
import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Dto.UserDto;
import com.example.stage2.HNG.Exception.InvalidInputException;
import com.example.stage2.HNG.Mapper.UserMapper;
import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.OrgRepository;
import com.example.stage2.HNG.Repository.UserRepository;
import com.example.stage2.HNG.Service.CreateNewUser;
import com.example.stage2.HNG.Service.CreateOrganization;
import com.example.stage2.HNG.Service.UserFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateNewUserImpl implements CreateNewUser {

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


    @Override
    public Response<UserResponseData> addNewUser(UserDto userDto) throws InvalidInputException {
        User user = UserMapper.mapToUser(userDto,passwordEncoder);
        Long count = userRepository.count();
        Long id = ++count;
        user.setUserId(String.format("Id/%04d",id));
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
        orgRepository.save(organization);
        UserResponse userResponse = UserMapper.mapToUserResponse(save);
        UserResponseData userResponseData = new UserResponseData("Hello",userResponse);
        Response<UserResponseData> response = new Response<>("Success", "You have registered Successfully",userResponseData);
        return response;
    }
}
