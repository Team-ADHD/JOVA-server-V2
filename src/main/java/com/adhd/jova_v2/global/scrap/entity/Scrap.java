package com.adhd.jova_v2.global.scrap.entity;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.users.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "scraps")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;
    @Column(name = "scrap_job_id", nullable = false)
    @OneToMany(fetch = FetchType.LAZY)
    private List<Job> scrapJobs;

    public void addScrapJob(Job job) {
        this.scrapJobs.add(job);
    }

    public void removeScrapJob(Job job) {
        this.scrapJobs.remove(job);
    }
}