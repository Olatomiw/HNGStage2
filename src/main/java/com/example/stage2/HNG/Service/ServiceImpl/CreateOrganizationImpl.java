package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.Dto.OrganizationDto;
import com.example.stage2.HNG.Mapper.OrganizationMapper;
import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Repository.OrgRepository;
import com.example.stage2.HNG.Service.CreateOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateOrganizationImpl implements CreateOrganization {
    @Autowired
    private OrgRepository orgRepository;
    @Override
    public Organization defaultOrganization(OrganizationDto organizationDto) {
        try{
            long count = orgRepository.count();
            long newCount = ++count;
            Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
            organization.setOrgId(String.format("OrgId/%04d", newCount ));
            orgRepository.save(organization);
            return organization;
        }
        catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}
