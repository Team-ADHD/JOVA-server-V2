package com.adhd.jova_v2.global.security.jwt.service;

import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.enums.role.UserRole;

import java.util.UUID;

public interface JwtIssueService {
    TokenDto issueAccessToken(UUID userId, UserRole role);

    TokenDto issueRefreshToken(UUID userId);
}