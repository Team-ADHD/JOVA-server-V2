package com.adhd.jova_v2.global.applications.dto;

import com.adhd.jova_v2.global.applications.entity.Application;
import com.adhd.jova_v2.global.applications.enums.ApplicationStatus;
import com.adhd.jova_v2.global.jobs.dto.JobDto;
import com.adhd.jova_v2.global.majors.dto.MajorDto;
import com.adhd.jova_v2.global.users.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private Long id;
    private JobDto job;
    private UserDto applicant;
    private MajorDto major;
    private String content;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts an Application entity to an ApplicationDto.
     *
     * @param application The Application entity to be converted
     * @return An ApplicationDto representation of the input Application
     * @throws NullPointerException if the input application is null
     */
    public static ApplicationDto fromEntity(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .job(application.getJob() != null ? JobDto.fromEntity(application.getJob()) : null)
                .applicant(application.getApplicant() != null ? UserDto.fromEntity(application.getApplicant()) : null)
                .major(application.getMajor() != null ? MajorDto.fromEntity(application.getMajor()) : null)
                .content(application.getContent())
                .status(application.getStatus())
                .createdAt(application.getCreatedAt())
                .updatedAt(application.getUpdatedAt())
                .build();
    }

    /**
     * Converts the ApplicationDto to an Application entity.
     *
     * @return An Application entity with fields mapped from the current ApplicationDto
     * 
     * This method creates a new Application entity using the builder pattern, mapping
     * each field from the ApplicationDto. Nested DTOs (job, applicant, major) are 
     * converted to their respective entities if they are not null.
     */
    public Application toEntity() {
        return Application.builder()
                .id(this.id)
                .job(this.job != null ? this.job.toEntity() : null)
                .applicant(this.applicant != null ? this.applicant.toEntity() : null)
                .major(this.major != null ? this.major.toEntity() : null)
                .content(this.content)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}