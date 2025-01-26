package com.adhd.jova_v2.global.scrap.repository;

import com.adhd.jova_v2.global.scrap.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Scrap findByUserId(Long userId);
}