package com.anas.blogapi.authentication.controller;

import com.anas.blogapi.authentication.Dto.Required.LoginRequest;
import com.anas.blogapi.authentication.Dto.Response.LoginResponse;
import com.anas.blogapi.authentication.Dto.Response.signUpResponse;
import com.anas.blogapi.authentication.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
@PostMapping("/signup")
    ResponseEntity<signUpResponse> signup(@Valid @RequestBody LoginRequest signupRequest) {
        return ResponseEntity.ok(authService.signup(signupRequest));
    }
}
