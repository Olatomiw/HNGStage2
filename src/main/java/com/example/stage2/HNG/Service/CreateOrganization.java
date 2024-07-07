package com.example.stage2.HNG.Service;

import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Model.Organization;
import org.springframework.http.ResponseEntity;

public interface CreateOrganization {

    Organization defaultOrganization(OrganizationDto organizationDto);
}
