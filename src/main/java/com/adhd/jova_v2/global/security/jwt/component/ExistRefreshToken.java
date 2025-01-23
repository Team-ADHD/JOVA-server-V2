package com.adhd.jova_v2.global.security.jwt.component;

import com.adhd.jova_v2.global.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExistRefreshToken {

    private final RedisUtil redisUtil;

    public boolean isExist(String token) {
        try {
            return redisUtil.hasKey(token, false);
        } catch (Exception e) {
            log.error("Failed to check if token duplicate due to Redis error: {}", e.getMessage(), e);
            return false;
        }
    }
}
