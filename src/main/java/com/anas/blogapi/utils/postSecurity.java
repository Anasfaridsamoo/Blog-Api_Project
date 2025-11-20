package com.anas.blogapi.utils;

import com.anas.blogapi.respositories.PostRepo;
import com.anas.blogapi.respositories.UserRepo;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class postSecurity {

    private final PostRepo postRepository;
    private final UserRepo userRepo;

    public boolean isPostOwner( int id, Authentication authentication) {

        // allow admin
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin) return true;

        String loggedEmail = authentication.getName();

        return postRepository.findById(id)
                .map(post -> post.getUser().getEmail().equals(loggedEmail))
                .orElse(false);
    }
    public boolean isSelf(Integer userId, Authentication authentication) {
        String loggedEmail = authentication.getName(); // from Principal

        return userRepo.findById(userId)
                .map(user -> user.getEmail().equals(loggedEmail))
                .orElse(false);
    }
}
