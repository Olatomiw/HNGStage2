package com.example.stage2.HNG.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "org_table")
public class Organization {

    @Id
    @Column(unique = true)
    private String orgId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "organizations")
    private Set<User> users = new HashSet<>();

    public Organization(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
