package com.adhd.jova_v2.global.jobs.entity;

import com.adhd.jova_v2.global.applications.entity.Application;
import com.adhd.jova_v2.global.jobs.enums.JobStatus;
import com.adhd.jova_v2.global.majors.entity.Major;
import com.adhd.jova_v2.global.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@BatchSize(size = 100)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @Size(min = 10, max = 7500, message = "Content must be between 10 and 7500 characters")
    private String description;
    @Column(name = "deadline", nullable = false)
    private LocalDateTime closingDate;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "job_required_majors",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private List<Major> requiredMajors = new ArrayList<>();
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public void addApplication(Application application) {
        this.applications.add(application);
        application.setJob(this);
    }

    public void removeApplication(Application application) {
        this.applications.remove(application);
        application.setJob(null);
    }

    public void addRequiredMajor(Major major) {
        this.requiredMajors.add(major);
        major.addJob(this);
    }

    public void removeRequiredMajor(Major major) {
        this.requiredMajors.remove(major);
        major.removeJob(this);
    }

    @PrePersist
    public void onCreated() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdated() {
        this.updatedAt = LocalDateTime.now();
    }
}