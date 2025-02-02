package com.adhd.jova_v2.global.jobs.controller;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.jobs.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping("/{id}")
    public Optional<Job> findSingleJobById (@PathVariable long id) {
        return jobService.findJobById(id);
    }

    @GetMapping("/")
    public List<Job> findAllJobs () {
        return jobService.findAllJobs();
    }

    @PostMapping("/")
    public void postSingleJob (Job job) {
        jobService.postJob(job);
    }

    @PatchMapping("/{id}")
    public void editSingleJob (@PathVariable long id, Job requestJob) {
        jobService.editJob(id, requestJob);
    }

    @DeleteMapping("/{id}")
    public void deleteSingleJob (@PathVariable long id) {
        jobService.deleteJob(id);
    }
}
