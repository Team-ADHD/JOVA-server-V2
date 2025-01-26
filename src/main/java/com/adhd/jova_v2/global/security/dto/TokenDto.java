package com.adhd.jova_v2.global.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TokenDto {
    private String token;
    private LocalDateTime expiredAt;
}