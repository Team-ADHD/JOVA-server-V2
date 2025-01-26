package com.adhd.jova_v2.global.alarm.repository;

import com.adhd.jova_v2.global.alarm.entity.Alarm;
import com.adhd.jova_v2.global.alarm.enums.AlarmStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    Alarm findByUserId(Long userId);
}