package com.adhd.jova_v2.global.security.jwt.component;

import com.adhd.jova_v2.global.redis.util.RedisUtil;
import com.adhd.jova_v2.global.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveRefreshToken {

    private final RedisUtil redisUtil;

    public boolean save(TokenDto tokenDto, UUID userId) {
        try {
            redisUtil.set(tokenDto.getToken(), userId, 604800, TimeUnit.SECONDS, false);
        } catch (DataAccessException e) {
            log.error("Failed to save refresh token due to Redis error: {}", e.getMessage(), e);
            return false;
        } catch (Exception e) {
            log.error("Unexpected error occurred while saving refresh token: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }
}