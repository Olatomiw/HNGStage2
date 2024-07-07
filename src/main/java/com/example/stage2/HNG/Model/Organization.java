package com.example.stage2.HNG.Model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String orgId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "organizations")
    private Set<User> users = new HashSet<>();

}
