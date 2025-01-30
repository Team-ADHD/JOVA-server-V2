package com.adhd.jova_v2.global.security.jwt.service.impl;

import com.adhd.jova_v2.global.security.jwt.component.DeleteRefreshToken;
import com.adhd.jova_v2.global.security.jwt.component.ExistBlacklist;
import com.adhd.jova_v2.global.security.jwt.component.SaveBlacklist;
import com.adhd.jova_v2.global.security.jwt.service.JwtBlacklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtBlacklistServiceImpl implements JwtBlacklistService {

    private final SaveBlacklist saveBlacklist;
    private final DeleteRefreshToken deleteRefreshToken;
    private final ExistBlacklist existBlacklist;

    @Override
    public boolean blacklist(String token) {
        if (existBlacklist.isBlacklisted(token)) {
            log.warn("Token is already blacklisted.");
            return true;
        }
        if (!deleteRefreshToken.delete(token)) {
            log.error("Failed to delete refresh token.");
            return false;
        }
        return saveBlacklist.save(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return existBlacklist.isBlacklisted(token);
    }
}