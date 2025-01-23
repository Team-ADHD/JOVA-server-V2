package com.adhd.jova_v2.domain.auth.service.impl;

import com.adhd.jova_v2.domain.auth.exception.SignInFailedException;
import com.adhd.jova_v2.domain.auth.presentation.dto.request.SignInRequest;
import com.adhd.jova_v2.domain.auth.presentation.dto.response.TokenResponse;
import com.adhd.jova_v2.domain.auth.service.SignInService;
import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.jwt.service.JwtIssueService;
import com.adhd.jova_v2.global.users.component.FindUser;
import com.adhd.jova_v2.global.users.dto.UserDto;
import com.adhd.jova_v2.global.users.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final JwtIssueService jwtIssueService;
    private final FindUser findUser;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse execute(SignInRequest request) {
        log.info("Sign in request: {}", request.getEmail());
        try {
            if (findUser.isExistUser(request.getEmail())) {
                UserDto user = findUser.findUserByEmail(request.getEmail());
                if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    throw new UserNotFoundException("Sign in failed. Password not matched.");
                }
                TokenDto accessToken = jwtIssueService.issueAccessToken(user.getUuid(), user.getRole());
                TokenDto refreshToken = jwtIssueService.issueRefreshToken(user.getUuid());
                return TokenResponse.builder()
                        .accessToken(accessToken.getToken())
                        .refreshToken(refreshToken.getToken())
                        .accessTokenExpiredAt(accessToken.getExpiredAt())
                        .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                        .role(user.getRole())
                        .build();
            } else {
                throw new UserNotFoundException("Sign in failed. User not found.");
            }
        } catch (Exception e) {
            log.error("Sign in failed: {}", e.getMessage());
            throw new SignInFailedException("Sign in failed:" + e.getMessage());
        }
    }
}