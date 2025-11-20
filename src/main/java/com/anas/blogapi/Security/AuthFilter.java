package com.anas.blogapi.Security;

import com.anas.blogapi.authentication.service.AuthService;
import com.anas.blogapi.authentication.service.myUserDetails;
import com.anas.blogapi.entities.User;
import com.anas.blogapi.exceptions.ResourceNotFoundException;
import com.anas.blogapi.respositories.UserRepo;
import com.anas.blogapi.utils.AuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = authHeader.split("Bearer ")[1];
            String Email = authUtil.extractEmailFromToken(token);

            if (Email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepo.findByEmail(Email).orElseThrow(() -> new ResourceNotFoundException("User", "email: ", Email));
                myUserDetails userDetails = new myUserDetails(user);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }catch (Exception e){
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
}
}
