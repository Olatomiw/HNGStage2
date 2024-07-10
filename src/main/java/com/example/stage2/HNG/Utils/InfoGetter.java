package com.example.stage2.HNG.Utils;

import com.example.stage2.HNG.ApiResponse.AuthResponse.AuthResponseDto;
import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Mapper.OrganizationMapper;
import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.OrgRepository;
import com.example.stage2.HNG.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InfoGetter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrgRepository orgRepository;


    public User verifyLoginCredentials(String email)throws InvalidLoginCredentials {
        Optional<User>optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new InvalidLoginCredentials("Bad Cerdentials", HttpStatusCode.valueOf(401));

        }
        return optionalUser.get();
    }

    public String getEmailOfLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal()instanceof UserDetails){
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return username;
        }
        return "INTERNAL SERVER ERROR!!!!!!!";
    }

    public User getUserByEmail(String email) throws InvalidLoginCredentials {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isEmpty()){
            throw  new InvalidLoginCredentials("Bad Credentials", HttpStatusCode.valueOf(402));
        }
        return byEmail.get();
    }
    
    public User getUserByUserId(String userId) throws InvalidLoginCredentials {
        Optional<User> byUserId = userRepository.findByUserId(userId);
        if (byUserId.isEmpty()){
            throw new InvalidLoginCredentials("User Not Found", HttpStatusCode.valueOf(422));
        }
        return byUserId.get();
    }

    public Organization getOrganisationById(String orgId) throws InvalidLoginCredentials {
        Optional<Organization> byOrgId = orgRepository.findByOrgId(orgId);
        if (byOrgId.isEmpty()){
            throw new InvalidLoginCredentials("Organization Doesn't Exist", HttpStatusCode.valueOf(401));
        }
        return byOrgId.get();
    }
    public Organization generateOrganizationId(OrganizationDto organizationDto) throws InvalidLoginCredentials {
        long count = orgRepository.count();
        long newCount = ++count;
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        organization.setOrgId(String.format("OrgId-%03d", newCount ));
        Optional<Organization> byOrgId = orgRepository.findByOrgId(organization.getOrgId());
        if (byOrgId.isPresent()){
            throw new InvalidLoginCredentials("Organization Already Exist", HttpStatusCode.valueOf(401));
        }
        return organization;
    }


}
