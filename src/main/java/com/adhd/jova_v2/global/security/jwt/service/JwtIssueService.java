package com.adhd.jova_v2.global.security.jwt.service;

import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.enums.role.UserRole;

public interface JwtIssueService {
    TokenDto issueAccessToken(String userId, UserRole role);

    TokenDto issueRefreshToken(String userId);
}