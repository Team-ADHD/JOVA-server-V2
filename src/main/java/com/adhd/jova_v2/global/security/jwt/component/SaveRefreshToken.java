package com.adhd.jova_v2.global.security.jwt.component;

import com.adhd.jova_v2.global.security.dto.TokenDto;
import com.adhd.jova_v2.global.security.jwt.entity.RefreshToken;
import com.adhd.jova_v2.global.security.jwt.repository.RefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveRefreshToken {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    public boolean save(TokenDto tokenDto) {
        try {
            refreshTokenRedisRepository.save(
                    RefreshToken.builder()
                            .refreshToken(tokenDto.getToken()).
                            expiredAt(tokenDto.getExpiredAt().toString())
                            .build()
            );
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