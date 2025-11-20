package com.anas.blogapi.utils;

import com.anas.blogapi.authentication.service.myUserDetails;
import com.anas.blogapi.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class AuthUtil {
@Value("${jwt.secret}")
    private String jwtSecretKey;

    private SecretKey secretKey() {
        return io.jsonwebtoken.security.Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    }

    public String GenerateAccessToken(myUserDetails user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("UserID", user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(secretKey())
                .compact();
    }
    public Claims claims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmailFromToken(String token) {

        return claims(token).getSubject();
    }
}
