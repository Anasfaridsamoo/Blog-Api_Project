package com.anas.blogapi.authentication.service;

import com.anas.blogapi.entities.User;
import com.anas.blogapi.exceptions.ResourceNotFoundException;
import com.anas.blogapi.respositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class userDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user= userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","Email",username));
         return new myUserDetails(user);
    }
}
