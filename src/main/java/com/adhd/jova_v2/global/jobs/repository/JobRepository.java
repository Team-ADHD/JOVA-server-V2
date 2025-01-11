package com.adhd.jova_v2.global.jobs.repository;

import com.adhd.jova_v2.global.jobs.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> { }