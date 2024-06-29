package com.petwellnes.petwellnes_backend.security;

import com.petwellnes.petwellnes_backend.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "your-secret-key"; // replace with your secret key

    public String getToken(UserDetails userDetails, User user) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
