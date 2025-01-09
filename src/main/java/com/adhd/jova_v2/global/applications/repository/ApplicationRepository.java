package com.adhd.jova_v2.global.applications.repository;

import com.adhd.jova_v2.global.applications.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> { }