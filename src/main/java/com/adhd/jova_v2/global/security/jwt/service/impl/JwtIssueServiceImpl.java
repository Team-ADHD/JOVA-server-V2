package com.adhd.jova_v2.global.security.jwt.service.impl;

import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.security.exception.RefreshTokenSaveException;
import com.adhd.jova_v2.global.security.jwt.component.SaveRefreshToken;
import com.adhd.jova_v2.global.security.jwt.service.JwtIssueService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtIssueServiceImpl implements JwtIssueService {

    private final SaveRefreshToken saveRefreshToken;
    @Value("${spring.security.jwt.access-token-expiration}")
    private Long accessTokenExpiration;
    @Value("${spring.security.jwt.refresh-token-expiration}")
    private Long refreshTokenExpiration;
    @Value("${spring.security.jwt.secret}")
    private String secretKey;
    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

    @Override
    public TokenDto issueAccessToken(String userId, UserRole role) {
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(accessTokenExpiration);
        String accessToken = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiration.atZone(java.time.ZoneId.systemDefault()).toInstant()))
                .claim("role", role)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return new TokenDto(accessToken, expiration);
    }

    @Override
    public TokenDto issueRefreshToken(String userId) {
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(refreshTokenExpiration);
        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiration.atZone(java.time.ZoneId.systemDefault()).toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        TokenDto tokenDto = new TokenDto(refreshToken, expiration);
        if (!saveRefreshToken.save(tokenDto)) {
            throw new RefreshTokenSaveException("Refresh token save failed");
        }
        return tokenDto;
    }
}