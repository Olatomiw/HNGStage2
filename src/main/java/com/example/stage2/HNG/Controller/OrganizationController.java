package com.example.stage2.HNG.Controller;

import com.example.stage2.HNG.ApiResponse.AddUserToOrgResponse;
import com.example.stage2.HNG.ApiResponse.Success.Response;
import com.example.stage2.HNG.Dto.AddUserRequest;
import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Service.CreateOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/organisation")
public class OrganizationController {

    @Autowired
    private CreateOrganization createOrganization;

    @PostMapping("/api/organisation")
    public Response<Organization> createOrganization(@RequestBody OrganizationDto organizationDto) throws InvalidLoginCredentials {
        return createOrganization.createOrganization(organizationDto);
    }

    @GetMapping("api/organisation/{id}")
    public ResponseEntity<?>getOrganizationById(@PathVariable String id) throws InvalidLoginCredentials {
        return createOrganization.getOrganisationById(id);
    }

    @PostMapping("/api/organisation/{orgId}/users")
    public ResponseEntity<?> addUserToOrganisation(@PathVariable String orgId ,@RequestBody AddUserRequest addUserRequest) throws InvalidLoginCredentials {
        AddUserToOrgResponse addUserToOrgResponse = createOrganization.addUserToOrganisation(orgId, addUserRequest.getUserId());
        return new ResponseEntity<>(addUserToOrgResponse, HttpStatus.OK);
    }

    @GetMapping("/api/organisation")
    public ResponseEntity<?> getAllMyOrganisation() throws InvalidLoginCredentials {
        ResponseEntity<?> allLoggedInUserOrganisation = createOrganization.getAllLoggedInUserOrganisation();
        return new ResponseEntity<>(allLoggedInUserOrganisation.getBody(), HttpStatus.OK);
    }
}
