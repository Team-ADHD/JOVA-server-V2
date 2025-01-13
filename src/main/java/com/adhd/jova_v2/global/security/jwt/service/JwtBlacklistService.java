package com.adhd.jova_v2.global.security.jwt.service;

public interface JwtBlacklistService {
    boolean blacklist(String token);

    boolean isBlacklisted(String token);
}