package com.example.stage2.HNG.Repository;

import com.example.stage2.HNG.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgRepository extends JpaRepository<Organization, Long> {


    Optional<Organization> findByOrgId(String orgId);
}
