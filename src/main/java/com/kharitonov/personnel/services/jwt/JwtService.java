package com.kharitonov.personnel.services.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Claims extractAllClaims(String token);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);

}
