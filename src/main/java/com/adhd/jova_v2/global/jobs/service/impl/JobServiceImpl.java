package com.adhd.jova_v2.global.jobs.service.impl;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.jobs.enums.JobStatus;
import com.adhd.jova_v2.global.jobs.repository.JobRepository;
import com.adhd.jova_v2.global.jobs.service.JobService;
import com.adhd.jova_v2.global.majors.entity.Major;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    @Override
    public List<Job> findAllJobs(){
        return jobRepository.findAll();
    }
    @Override
    public Optional<Job> findJobById(long id){
        return jobRepository.findById(id);
    }
    @Override
    public void postJob(Job job){
        jobRepository.save(job);
    }
    @Override
    @Transactional
    public void editJob(long id, Job requestJob){
        Optional<Job> existingJob = jobRepository.findById(id);
        if (existingJob.isPresent()){
           Job updatedJob = existingJob.get();
           updatedJob.builder()
                   .id(id)
                   .title(requestJob.getTitle())
                   .description(requestJob.getDescription())
                   .status(requestJob.getStatus())
                   .build();
           jobRepository.save(updatedJob);
        }
        else {
            throw new IllegalArgumentException("Job not found");
        }
    }
    @Override
    public void deleteJob(long id) {
        jobRepository.deleteById(id);
    }
    @Override
    public List<Job> findJobsByMajor (Major major){
        return findJobsByMajor(major);
    }
    @Override
    public List<Job> findJobsByStatus (JobStatus status){
        return findJobsByStatus(status);
    }
}
