package com.adhd.jova_v2.global.alarm.entity;

import com.adhd.jova_v2.global.alarm.dto.AlarmContent;
import com.adhd.jova_v2.global.alarm.enums.AlarmStatus;
import com.adhd.jova_v2.global.users.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "alarms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "alarm_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AlarmStatus alarmStatus;
    @ElementCollection
    @CollectionTable(name = "alarm_contents", joinColumns = @JoinColumn(name = "alarm_id"))
    private List<AlarmContent> alarmContents;
}