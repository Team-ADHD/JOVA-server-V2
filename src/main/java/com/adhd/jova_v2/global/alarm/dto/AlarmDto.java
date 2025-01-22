package com.adhd.jova_v2.global.alarm.dto;

import com.adhd.jova_v2.global.alarm.entity.Alarm;
import com.adhd.jova_v2.global.alarm.enums.AlarmStatus;
import com.adhd.jova_v2.global.users.entity.User;
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
    private Long userId;
    private List<AlarmContent> alarmContents;

    public static AlarmDto fromEntity(Alarm alarm) {
        return AlarmDto.builder()
                .id(alarm.getId())
                .userId(alarm.getUser().getId())
                .alarmContents(alarm.getAlarmContents())
                .build();
    }

    public Alarm toEntity() {
        return Alarm.builder()
                .id(this.id)
                .user(User.builder().id(this.userId).build())
                .alarmContents(this.alarmContents)
                .build();
    }
}