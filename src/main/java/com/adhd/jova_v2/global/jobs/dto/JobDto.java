package com.adhd.jova_v2.global.jobs.dto;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.jobs.enums.JobStatus;
import com.adhd.jova_v2.global.users.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class JobDto {
    private Long id;
    private UserDto user;
    private String title;
    private String description;
    private LocalDateTime closingDate;
    private JobStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Job toEntity() {
        return Job.builder()
                .user(this.user.toEntity())
                .title(this.title)
                .description(this.description)
                .closingDate(this.closingDate)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}