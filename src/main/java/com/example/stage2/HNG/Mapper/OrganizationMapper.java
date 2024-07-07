package com.example.stage2.HNG.Mapper;

import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Model.Organization;

public class OrganizationMapper {

    public static Organization mapToOrganization(OrganizationDto organizationDto){
        Organization organization = new Organization(
                organizationDto.getName(),
                organizationDto.getDescription()
        );
        return organization;
    }
}
