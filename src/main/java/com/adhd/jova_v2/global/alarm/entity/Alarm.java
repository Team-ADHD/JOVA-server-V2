package com.adhd.jova_v2.global.alarm.entity;

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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    @ElementCollection
    @CollectionTable(name = "alarm_contents", joinColumns = @JoinColumn(name = "alarm_id"))
    private List<AlarmContent> alarmContents;
}