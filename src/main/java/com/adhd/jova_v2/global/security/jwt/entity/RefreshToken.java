package com.adhd.jova_v2.global.security.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@RedisHash(value = "refresh_token", timeToLive = 2 * 24 * 60 * 60)
public class RefreshToken {
    private String refreshToken;
    private String expiredAt;
}