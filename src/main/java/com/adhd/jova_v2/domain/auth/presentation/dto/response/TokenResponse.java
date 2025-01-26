package com.adhd.jova_v2.domain.auth.presentation.dto.response;

import com.adhd.jova_v2.global.security.enums.role.UserRole;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    @Future(message = "Access token expired time must be in the future")
    private LocalDateTime accessTokenExpiredAt;
    @Future(message = "Refresh token expired time must be in the future")
    private LocalDateTime refreshTokenExpiredAt;
    private UserRole role;
}