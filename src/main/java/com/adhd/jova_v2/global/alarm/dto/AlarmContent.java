package com.adhd.jova_v2.global.alarm.dto;

import com.adhd.jova_v2.global.alarm.enums.AlarmLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AlarmContent {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlarmLevel level;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}