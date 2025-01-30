package com.adhd.jova_v2.global.security.jwt.component;

import com.adhd.jova_v2.global.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class SaveBlacklist {

    private final RedisUtil redisUtil;

    public boolean save(String token) {
        try {
            redisUtil.set(token, "BLACKLIST", 604800, TimeUnit.SECONDS, true);
        } catch (DataAccessException e) {
            log.error("Failed to save token to blacklist due to Redis error: {}", e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("Failed to save token to blacklist: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }
}