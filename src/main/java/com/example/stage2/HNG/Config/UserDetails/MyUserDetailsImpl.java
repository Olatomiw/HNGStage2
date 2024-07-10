package com.example.stage2.HNG.Config.UserDetails;

import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsImpl implements MyUserDetailsService{
    @Autowired
    private UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in the loadByUsername Method ................");
        Optional<User> byEmail = userRepository.findByEmail(username);
        if (byEmail.isEmpty()){
            logger.error("Username not found" + username);
            throw  new UsernameNotFoundException("Could not find User..........!");
        }
//        if (byEmail.get().getOrganizations()== null){
//            logger.error(username+" no organization is foound");
//            throw new RuntimeException("Empty role");
//        }
        return new CustomerDetails(byEmail.get());
    }
}
