package com.adhd.jova_v2.domain.auth.service.impl;

import com.adhd.jova_v2.domain.auth.presentation.dto.request.SignInRequest;
import com.adhd.jova_v2.domain.auth.presentation.dto.response.SignInResponse;
import com.adhd.jova_v2.domain.auth.service.SignInService;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.security.jwt.service.JwtIssueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final JwtIssueService jwtIssueService;

    @Override
    public SignInResponse execute(SignInRequest request) {
         // 아직 구현 안됨(임시로 넣어둔 값)
    }
}
