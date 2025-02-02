package com.adhd.jova_v2.global.jobs.controller;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.jobs.enums.JobStatus;
import com.adhd.jova_v2.global.jobs.service.JobService;
import com.adhd.jova_v2.global.majors.entity.Major;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs/search")
public class JobSearchController {
    private final JobService jobService;

    @GetMapping("/")
    public List<Job> getJobsFilteredByMajor (@RequestParam("major") Major major) {
        return jobService.findJobsByMajor(major);
    }

    @GetMapping("/")
    public List<Job> getJobsfilteredByStatus (@RequestParam("status") JobStatus status) {
        return jobService.findJobsByStatus(status);
    }
}
