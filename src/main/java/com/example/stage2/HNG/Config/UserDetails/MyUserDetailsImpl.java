package com.example.stage2.HNG.Config.UserDetails;

import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsImpl implements MyUserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> byEmail = userRepository.findByEmail(username);

        if (byEmail == null){
            throw  new UsernameNotFoundException("Could not find User..........!");
        }
        return new CustomerDetails(byEmail.get());
    }
}
