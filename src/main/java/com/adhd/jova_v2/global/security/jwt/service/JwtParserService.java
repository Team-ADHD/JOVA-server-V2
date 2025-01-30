package com.adhd.jova_v2.global.security.jwt.service;

import com.adhd.jova_v2.global.security.enums.role.UserRole;

import java.util.UUID;

public interface JwtParserService {
    UUID extractUuid(String token);

    UserRole extractUserRole(String token);

    boolean validateToken(String token);

    boolean validateRefreshToken(String token);
}