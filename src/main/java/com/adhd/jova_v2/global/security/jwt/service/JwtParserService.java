package com.adhd.jova_v2.global.security.jwt.service;

import com.adhd.jova_v2.global.security.enums.role.UserRole;

public interface JwtParserService {
    String extractUserEmail(String token);

    UserRole extractUserRole(String token);

    boolean validateToken(String token);
}