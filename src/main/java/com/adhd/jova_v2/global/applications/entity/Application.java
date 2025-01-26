package com.adhd.jova_v2.global.applications.entity;

import com.adhd.jova_v2.global.applications.enums.ApplicationStatus;
import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.majors.entity.Major;
import com.adhd.jova_v2.global.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User applicant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;
    @Column(name = "content", columnDefinition = "TEXT")
    @Size(min = 10, max = 7500, message = "Content must be between 10 and 7500 characters")
    private String content;
    @Column(name = "status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}