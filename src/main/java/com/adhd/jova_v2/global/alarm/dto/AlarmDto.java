package com.adhd.jova_v2.global.alarm.dto;

import com.adhd.jova_v2.global.alarm.entity.Alarm;
import com.adhd.jova_v2.global.alarm.entity.AlarmContent;
import com.adhd.jova_v2.global.users.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AlarmDto {
    private Long id;
    private UserDto user;
    private List<AlarmContent> alarmContents;

    public static AlarmDto fromEntity(Alarm alarm) {
        return AlarmDto.builder()
                .id(alarm.getId())
                .user(alarm.getUser() != null ? UserDto.fromEntity(alarm.getUser()) : null)
                .alarmContents(alarm.getAlarmContents())
                .build();
    }

    public Alarm toEntity() {
        return Alarm.builder()
                .id(this.id)
                .user(this.user != null ? this.user.toEntity() : null)
                .alarmContents(this.alarmContents)
                .build();
    }
}