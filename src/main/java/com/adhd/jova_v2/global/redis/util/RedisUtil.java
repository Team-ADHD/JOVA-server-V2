package com.adhd.jova_v2.global.redis.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> authCodeRedisTemplate;
    private final RedisTemplate<String, Object> blackListRedisTemplate;

    public void set(String key, Object value, int ttl, TimeUnit timeUnit, boolean isBlackList) {
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        template.opsForValue().set(key, value, ttl, timeUnit);
    }

    public Object get(String key, boolean isBlackList) {
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        return template.opsForValue().get(key);
    }

    public boolean delete(String key, boolean isBlackList) {
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        return template.delete(key);
    }

    public boolean hasKey(String key, boolean isBlackList) {
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        return Boolean.TRUE.equals(template.hasKey(key));
    }
}