package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.ApiResponse.AddUserToOrgResponse;
import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Exception.ExceptionResponses.GeneralExceptionResponse;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Exception.UserAlreadyBelongToOrgException;
import com.example.stage2.HNG.Mapper.OrganizationMapper;
import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.OrgRepository;
import com.example.stage2.HNG.Repository.UserRepository;
import com.example.stage2.HNG.Service.CreateOrganization;
import com.example.stage2.HNG.Utils.InfoGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CreateOrganizationImpl implements CreateOrganization {
    @Autowired
    private OrgRepository orgRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfoGetter infoGetter;
    @Override
    public Organization defaultOrganization(OrganizationDto organizationDto) {
        try{
            Organization organization = infoGetter.generateOrganizationId(organizationDto);
            orgRepository.save(organization);
            return organization;
        }
        catch (Exception | InvalidLoginCredentials e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public Response<Organization> createOrganization(OrganizationDto organizationDto) throws InvalidLoginCredentials {
        String emailOfLoggedInUser = infoGetter.getEmailOfLoggedInUser();
        User userByEmail = infoGetter.getUserByEmail(emailOfLoggedInUser);
        Organization organization = infoGetter.generateOrganizationId(organizationDto);
        userByEmail.getOrganizations().add(organization);
        orgRepository.save(organization);
        userRepository.save(userByEmail);
        Response<Organization> response = new Response<>("Success", "Organization Created Successfully", organization);
        return response;
    }

    @Override
    public ResponseEntity<?> getOrganisationById(String id) throws InvalidLoginCredentials {
        String emailOfLoggedInUser = infoGetter.getEmailOfLoggedInUser();
        User userByEmail = infoGetter.getUserByEmail(emailOfLoggedInUser);
        Organization organisationById = infoGetter.getOrganisationById(id);

        if (!organisationById.getUsers().contains(userByEmail)) {
            return new ResponseEntity<>(new GeneralExceptionResponse(
                    "FORBIDDEN",
                    "Invalid request",
                    HttpStatusCode.valueOf(401)
            ), HttpStatus.FORBIDDEN);
        }
        Response<Organization> response = new Response<>("Success", "Confirmed", organisationById);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public AddUserToOrgResponse addUserToOrganisation(String orgId, String userId) throws InvalidLoginCredentials {
        Organization organisationById = infoGetter.getOrganisationById(orgId);
        User userByUserId = infoGetter.getUserByUserId(userId);
        String firstName = userByUserId.getFirstName();
        Set<Organization> organizations = userByUserId.getOrganizations();
        if (organizations.contains(organisationById)){
            throw new UserAlreadyBelongToOrgException(firstName + " already belong to organisation");
        }
        organizations.add(organisationById);
        userByUserId.setOrganizations(organizations);
        userRepository.save(userByUserId);
        AddUserToOrgResponse addUserToOrgResponse = new AddUserToOrgResponse(
                "success",
                "User added To Organisation Successfully");
        return addUserToOrgResponse;
    }

    @Override
    public ResponseEntity<?> getAllLoggedInUserOrganisation() throws InvalidLoginCredentials {
        String emailOfLoggedInUser = infoGetter.getEmailOfLoggedInUser();
        User userByEmail = infoGetter.getUserByEmail(emailOfLoggedInUser);
        Set<Organization> organizations = userByEmail.getOrganizations();
        if (organizations.isEmpty() || organizations == null){
            throw new UserAlreadyBelongToOrgException("You don't belong to any organisation");
        }
        Response<Set<Organization>> response= new Response<>();
        response.setStatus("Success");
        response.setMessage("List of all the organisation you belong to");
        response.setData(organizations);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
