package com.adhd.jova_v2.global.security.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@RedisHash(value = "refresh_token", timeToLive = 604800)
public class RefreshToken {
    @Id
    private String refreshToken;
    @Indexed
    private UUID userId;
    private LocalDateTime expiredAt;
}