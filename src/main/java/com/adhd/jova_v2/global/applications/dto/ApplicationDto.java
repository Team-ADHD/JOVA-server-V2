package com.adhd.jova_v2.global.applications.dto;

import com.adhd.jova_v2.global.applications.entity.Application;
import com.adhd.jova_v2.global.applications.enums.ApplicationStatus;
import com.adhd.jova_v2.global.jobs.dto.JobDto;
import com.adhd.jova_v2.global.majors.dto.MajorDto;
import com.adhd.jova_v2.global.users.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApplicationDto {
    private Long id;
    private JobDto job;
    private UserDto user;
    private MajorDto major;
    private String content;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Application toEntity() {
        return Application.builder()
                .job(this.job.toEntity())
                .applicant(this.user.toEntity())
                .major(this.major.toEntity())
                .content(this.content)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}