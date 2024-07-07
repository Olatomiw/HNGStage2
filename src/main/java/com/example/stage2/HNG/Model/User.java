package com.example.stage2.HNG.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "org_user")
@AllArgsConstructor
@NoArgsConstructor


public class User {

    @Id
    @Column(unique = true, nullable = false)
    private String userId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @Size(min = 8)
    private String password;
    @Column(nullable = false)
    private String phone;

    public User(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
