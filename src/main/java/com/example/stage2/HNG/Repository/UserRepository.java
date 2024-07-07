package com.example.stage2.HNG.Repository;

import com.example.stage2.HNG.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
