package com.anas.blogapi.authentication.service;

import com.anas.blogapi.Dto.Response.UserResDto;
import com.anas.blogapi.authentication.Dto.Required.LoginRequest;
import com.anas.blogapi.authentication.Dto.Response.LoginResponse;
import com.anas.blogapi.authentication.Dto.Response.signUpResponse;
import com.anas.blogapi.entities.User;
import com.anas.blogapi.respositories.UserRepo;
import com.anas.blogapi.respositories.roleRepo;
import com.anas.blogapi.services.UserService;
import com.anas.blogapi.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final roleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        myUserDetails user = (myUserDetails) authentication.getPrincipal();
        String token = authUtil.GenerateAccessToken(user);
        return new LoginResponse(token, user.getId());
    }

    public signUpResponse signup(LoginRequest signupRequest) {
        User user = this.userRepo.findByEmail(signupRequest.getEmail()).orElse(null);
        if (user != null) {
            throw new IllegalArgumentException("User already exists");
        }
       User newUser = User.builder()
               .name(signupRequest.getEmail().split("@")[0])
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
               .roles(Set.of(roleRepo.findById(1).get()))
                .build();
        User savedUser = this.userRepo.save(newUser);
        myUserDetails userDetails = new myUserDetails(savedUser);
        return new signUpResponse(userDetails.getId(), userDetails.getEmail());

    }
}
