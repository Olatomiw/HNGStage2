package com.example.stage2.HNG.Service;

import com.example.stage2.HNG.ApiResponse.AddUserToOrgResponse;
import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Model.Organization;
import org.springframework.http.ResponseEntity;

public interface CreateOrganization {

    Organization defaultOrganization(OrganizationDto organizationDto);

    Response<Organization> createOrganization(OrganizationDto organizationDto) throws InvalidLoginCredentials;

    ResponseEntity<?> getOrganisationById(String id) throws InvalidLoginCredentials;

    AddUserToOrgResponse addUserToOrganisation(String orgId, String userId) throws InvalidLoginCredentials;

    ResponseEntity<?> getAllLoggedInUserOrganisation() throws InvalidLoginCredentials;
}
