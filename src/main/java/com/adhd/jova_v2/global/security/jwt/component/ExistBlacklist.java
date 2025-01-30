package com.adhd.jova_v2.global.security.jwt.component;

import com.adhd.jova_v2.global.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExistBlacklist {

    private final RedisUtil redisUtil;

    public boolean isBlacklisted(String token) {
        try {
            return redisUtil.hasKey(token, true);
        } catch (Exception e) {
            log.error("Failed to check if token is blacklisted due to Redis error: {}", e.getMessage(), e);
            return false;
        }
    }
}