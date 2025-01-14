package com.adhd.jova_v2.global.jobs.dto;

import com.adhd.jova_v2.global.applications.dto.ApplicationDto;
import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.jobs.enums.JobStatus;
import com.adhd.jova_v2.global.majors.dto.MajorDto;
import com.adhd.jova_v2.global.users.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class JobDto {
    private Long id;
    private UserDto user;
    private String title;
    private String description;
    private LocalDateTime closingDate;
    private JobStatus status;
    private List<ApplicationDto> applications;
    private List<MajorDto> requiredMajors;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static JobDto fromEntity(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .user(job.getUser() != null ? UserDto.fromEntity(job.getUser()) : null)
                .title(job.getTitle())
                .description(job.getDescription())
                .closingDate(job.getClosingDate())
                .status(job.getStatus())
                .applications(job.getApplications() != null
                        ? job.getApplications().stream().map(ApplicationDto::fromEntity).toList()
                        : null)
                .requiredMajors(job.getRequiredMajors() != null
                        ? job.getRequiredMajors().stream().map(MajorDto::fromEntity).toList()
                        : null)
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .build();
    }

    public Job toEntity() {
        return Job.builder()
                .id(this.id)
                .user(this.user != null ? this.user.toEntity() : null)
                .title(this.title)
                .description(this.description)
                .closingDate(this.closingDate)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}