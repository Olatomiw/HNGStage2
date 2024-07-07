package com.example.stage2.HNG.Config.UserDetails;

import com.example.stage2.HNG.Model.Organization;
import com.example.stage2.HNG.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerDetails  implements UserDetails {
    private User user;

    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomerDetails (User user){
        this.username = user.getEmail();
        this.password = user.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();
        for (Organization organization : user.getOrganizations()){
            auths.add(new SimpleGrantedAuthority(organization.getName().toUpperCase()));

        }
        this.authorities = auths;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
