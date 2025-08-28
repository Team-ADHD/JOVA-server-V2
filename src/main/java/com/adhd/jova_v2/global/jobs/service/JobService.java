package com.adhd.jova_v2.global.jobs.service;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.jobs.enums.JobStatus;
import com.adhd.jova_v2.global.majors.entity.Major;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<Job> findJobById(long id);
    List<Job> findAllJobs();
    List<Job> findJobsByMajor(Major major);
    List<Job> findJobsByStatus(JobStatus status);
    void deleteJob(long id);
    void editJob(long id, Job requestJob);
    void postJob(Job job);
}
