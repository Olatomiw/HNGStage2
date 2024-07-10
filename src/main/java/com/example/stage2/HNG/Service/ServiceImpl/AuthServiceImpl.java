package com.example.stage2.HNG.Service.ServiceImpl;

import com.example.stage2.HNG.ApiResponse.AuthResponse.AuthResponseDto;
import com.example.stage2.HNG.ApiResponse.JwtResponseDto;
import com.example.stage2.HNG.Config.JwtConfig.JWTFilter;
import com.example.stage2.HNG.Exception.InvalidLoginCredentials;
import com.example.stage2.HNG.Model.User;
import com.example.stage2.HNG.Utils.InfoGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
    @Autowired
    private JWTFilter jwtFilter;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private InfoGetter infoGetter;


    public JwtResponseDto jwtResponseDto(AuthResponseDto authResponseDto) throws InvalidLoginCredentials {
        User user = infoGetter.verifyLoginCredentials(authResponseDto.getUsername());
        if (!passwordEncoder.matches(authResponseDto.getPassword(), user.getPassword()) || authResponseDto.getPassword() == null){
            throw new InvalidLoginCredentials("Bad credentials", HttpStatusCode.valueOf(401));
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authResponseDto.getUsername(),
                authResponseDto.getPassword()));
        if (authentication.isAuthenticated()){
            return JwtResponseDto.builder()
                    .token(jwtFilter.generateToken(authResponseDto.getUsername())).build();
        }
        else {
            throw new UsernameNotFoundException("Invalid Request");
        }
    }
}
