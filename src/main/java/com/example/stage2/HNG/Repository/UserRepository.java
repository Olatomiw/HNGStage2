package com.example.stage2.HNG.Repository;

import com.example.stage2.HNG.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByEmail(String email);
}
