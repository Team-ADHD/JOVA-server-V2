package com.adhd.jova_v2.global.security.jwt.event.listener;

import com.adhd.jova_v2.global.redis.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshTokenExpirationListener implements MessageListener {

    private final RedisUtil redisUtil;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        log.info("Expired Key detected: {}", expiredKey);
        if (!expiredKey.isEmpty()) {
            redisUtil.set(expiredKey, "BLACKLISTED", 604800, TimeUnit.SECONDS, true);
            log.info("Refresh token {} added to blacklist with value: {}", expiredKey, expiredKey);
        } else {
            log.warn("Failed to retrieve value for expired key: {}", expiredKey);
        }
    }
}