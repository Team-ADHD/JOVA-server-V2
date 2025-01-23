package com.adhd.jova_v2.domain.auth.service.impl;

import com.adhd.jova_v2.domain.auth.exception.InvalidRefreshTokenException;
import com.adhd.jova_v2.domain.auth.exception.TokenBlacklistedException;
import com.adhd.jova_v2.domain.auth.presentation.dto.response.TokenResponse;
import com.adhd.jova_v2.domain.auth.service.RefreshService;
import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.jwt.service.JwtBlacklistService;
import com.adhd.jova_v2.global.security.jwt.service.JwtIssueService;
import com.adhd.jova_v2.global.security.jwt.service.JwtParserService;
import com.adhd.jova_v2.global.users.component.FindUser;
import com.adhd.jova_v2.global.users.dto.UserDto;
import com.adhd.jova_v2.global.users.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshServiceImpl implements RefreshService {

    private final JwtIssueService jwtIssueService;
    private final JwtParserService jwtParserService;
    private final JwtBlacklistService jwtBlacklistService;
    private final FindUser findUser;

    @Override
    public TokenResponse execute(String refreshToken) {
        if (jwtBlacklistService.isBlacklisted(refreshToken)) {
            throw new TokenBlacklistedException("Refresh token is blacklisted.");
        }
        if (!jwtParserService.validateRefreshToken(refreshToken)) {
            log.error("Refresh token is invalid.");
            throw new InvalidRefreshTokenException("Refresh token is invalid.");
        }
        if (!jwtBlacklistService.blacklist(refreshToken)) {
            log.error("Failed to blacklist refresh token.");
            throw new TokenBlacklistedException("Failed to blacklist refresh token.");
        }
        UUID userUuid = jwtParserService.extractUuid(refreshToken);
        UserDto user = findUser.findUserByUuid(userUuid.toString());
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        TokenDto newAccessToken = jwtIssueService.issueAccessToken(user.getUuid(), user.getRole());
        TokenDto newRefreshToken = jwtIssueService.issueRefreshToken(user.getUuid());
        return TokenResponse.builder()
                .accessToken(newAccessToken.getToken())
                .refreshToken(newRefreshToken.getToken())
                .accessTokenExpiredAt(newAccessToken.getExpiredAt())
                .refreshTokenExpiredAt(newRefreshToken.getExpiredAt())
                .role(user.getRole())
                .build();
    }
}