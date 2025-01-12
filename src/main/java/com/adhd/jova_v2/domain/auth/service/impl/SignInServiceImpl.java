package com.adhd.jova_v2.domain.auth.service.impl;

import com.adhd.jova_v2.domain.auth.presentation.dto.response.SignInResponse;
import com.adhd.jova_v2.domain.auth.service.SignInService;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SignInServiceImpl implements SignInService {
    @Override
    public SignInResponse execute() {
        return new SignInResponse("token", "refreshToken", LocalDateTime.MAX, UserRole.ADMIN); // 아직 구현 안됨(임시로 넣어둔 값)
    }
}
