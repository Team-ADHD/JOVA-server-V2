package com.adhd.jova_v2.global.security.jwt.component;

import com.adhd.jova_v2.global.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeleteRefreshToken {

    private final RedisUtil redisUtil;

    public boolean delete(String token) {
        if (redisUtil.delete(token, false)) {
            log.info("Refresh token deleted successfully.");
            return true;
        } else {
            log.warn("Failed to delete refresh token.");
            return false;
        }
    }
}