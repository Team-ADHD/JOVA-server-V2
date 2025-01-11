package com.adhd.jova_v2.global.majors.entity;

import com.adhd.jova_v2.global.jobs.entity.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "majors", indexes = @Index(name = "major_name_index", columnList = "name"))
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "requiredMajors")
    private List<Job> jobs = new ArrayList<>();

    public void addJob(Job job) {
        this.jobs.add(job);
        job.getRequiredMajors().add(this);
    }

    public void removeJob(Job job) {
        this.jobs.remove(job);
        job.getRequiredMajors().remove(this);
    }
}