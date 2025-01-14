package com.adhd.jova_v2.domain.auth.service.impl;

import com.adhd.jova_v2.domain.auth.presentation.dto.request.SignInRequest;
import com.adhd.jova_v2.domain.auth.presentation.dto.response.SignInResponse;
import com.adhd.jova_v2.domain.auth.service.SignInService;
import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.jwt.service.JwtIssueService;
import com.adhd.jova_v2.global.users.component.FindUser;
import com.adhd.jova_v2.global.users.dto.UserDto;
import com.adhd.jova_v2.global.users.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final JwtIssueService jwtIssueService;
    private final FindUser findUser;

    @Override
    public SignInResponse execute(SignInRequest request) {
        if (findUser.isExistUser(request.getEmail())) {
            UserDto user = findUser.findUserByEmail(request.getEmail());
            TokenDto accessToken = jwtIssueService.issueAccessToken(user.getUuid(), user.getRole());
            TokenDto refreshToken = jwtIssueService.issueRefreshToken(user.getUuid());
            return SignInResponse.builder()
                    .accessToken(accessToken.getToken())
                    .refreshToken(refreshToken.getToken())
                    .accessTokenExpiredAt(accessToken.getExpiredAt())
                    .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                    .role(user.getRole())
                    .build();
        } else {
            throw new UserNotFoundException("Sign in failed. User not found.");
        }
    }
}