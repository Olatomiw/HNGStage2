package com.example.stage2.HNG.Repository;

import com.example.stage2.HNG.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orgRepository extends JpaRepository<Organization, Long> {
}
