package com.adhd.jova_v2.global.security.jwt.service.impl;

import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.security.exception.RefreshTokenSaveException;
import com.adhd.jova_v2.global.security.jwt.component.SaveRefreshToken;
import com.adhd.jova_v2.global.security.jwt.service.JwtIssueService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Slf4j
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
    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
        log.info("JwtIssueService Initialized successfully");
    }

    @Override
    public TokenDto issueAccessToken(UUID userId, UserRole role) {
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(accessTokenExpiration);
        String accessToken = Jwts.builder()
                .claim("jti", UUID.randomUUID().toString())
                .claim("aud", "jova-server")
                .claim("iss", "jova-client")
                .claim("sub", userId.toString())
                .claim("role", role)
                .claim("iat", LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .claim("exp", expiration.atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .signWith(key)
                .compact();
        return new TokenDto(accessToken, expiration);
    }

    @Override
    public TokenDto issueRefreshToken(UUID userId) {
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(refreshTokenExpiration);
        String refreshToken = Jwts.builder()
                .claim("aud", "jova-server")
                .claim("iss", "jova-client")
                .claim("sub", userId.toString())
                .claim("iat", LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .claim("exp", expiration.atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .signWith(key)
                .compact();
        TokenDto tokenDto = new TokenDto(refreshToken, expiration);
        if (!saveRefreshToken.save(tokenDto, userId)) {
            throw new RefreshTokenSaveException("Refresh token save failed");
        }
        return tokenDto;
    }
}