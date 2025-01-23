package com.adhd.jova_v2.global.security.jwt.service.impl;

import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.security.jwt.component.ExistRefreshToken;
import com.adhd.jova_v2.global.security.jwt.service.JwtParserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtParserServiceImpl implements JwtParserService {

    private final ExistRefreshToken existRefreshToken;
    @Value("${spring.security.jwt.secret}")
    private String secretKey;
    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
        log.info("JwtParserService Initialized successfully");
    }

    @Override
    public boolean validateToken(String token) {
        try {
            parserClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token is expired: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Token is invalid: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validateRefreshToken(String token) {
        return existRefreshToken.isExist(token);
    }

    @Override
    public UUID extractUuid(String token) {
        return UUID.fromString(parserClaims(token).getSubject());
    }

    @Override
    public UserRole extractUserRole(String token) {
        return parserClaims(token).get("role", UserRole.class);
    }

    private Claims parserClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}