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
        String namespacedKey = applyNamespace(key, isBlackList);
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        template.opsForValue().set(namespacedKey, value, ttl, timeUnit);
    }

    public Object get(String key, boolean isBlackList) {
        String namespacedKey = applyNamespace(key, isBlackList);
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        return template.opsForValue().get(namespacedKey);
    }

    public boolean delete(String key, boolean isBlackList) {
        String namespacedKey = applyNamespace(key, isBlackList);
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        return template.delete(namespacedKey);
    }

    public boolean hasKey(String key, boolean isBlackList) {
        String namespacedKey = applyNamespace(key, isBlackList);
        RedisTemplate<String, Object> template = isBlackList ? blackListRedisTemplate : authCodeRedisTemplate;
        return template.hasKey(namespacedKey);
    }

    private String applyNamespace(String key, boolean isBlackList) {
        return isBlackList ? "blacklist:" + key : key;
    }
}