package com.registrations.users.service.impl;

import com.registrations.users.model.User;
import com.registrations.users.repo.IUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@Data
@RequiredArgsConstructor
public class JwtService implements Serializable {

    private final IUserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;
    private long jwtExpiration=5 * 60 * 60 * 1000; // 5hs
    private long refreshExpiration=10 * 60 * 60 * 1000; //10hs

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public void generateToken( User user) {
        String createdToken= buildToken(user);
        user.setToken(createdToken);
        userRepository.save(user);
    }

    public String buildToken( User user) {

        return Jwts
                .builder()
                .claims(Map.of("name", user.getName()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

}