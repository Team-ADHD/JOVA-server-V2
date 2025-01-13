package com.adhd.jova_v2.global.security.jwt.service.impl;

import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.security.jwt.service.JwtParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtParserServiceImpl implements JwtParserService {
    // TODO: Implement this class
    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public String extractUserEmail(String token) {
        return "";
    }

    @Override
    public UserRole extractUserRole(String token) {
        return null;
    }
}
