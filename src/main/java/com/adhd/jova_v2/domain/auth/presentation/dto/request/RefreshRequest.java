package com.adhd.jova_v2.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RefreshRequest {
    @NotNull
    private String refreshToken;
}