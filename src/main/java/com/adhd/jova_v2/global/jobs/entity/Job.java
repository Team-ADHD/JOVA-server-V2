package com.adhd.jova_v2.global.jobs.entity;

import com.adhd.jova_v2.global.applications.entity.Application;
import com.adhd.jova_v2.global.jobs.enums.JobStatus;
import com.adhd.jova_v2.global.majors.entity.Major;
import com.adhd.jova_v2.global.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.sql.Timestamp;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    @Size(min = 10, max = 7500, message = "Content must be between 10 and 7500 characters")
    private String description;
    @Column(name = "deadline", nullable = false)
    private Timestamp closingDate;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications;
    @ManyToMany
    @JoinTable(
            name = "job_required_majors",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private List<Major> requiredMajors;

    public void addApplication(Application application) {
        this.applications.add(application);
        application.setJob(this);
    }

    public void removeApplication(Application application) {
        this.applications.remove(application);
        application.setJob(null);
    }
}