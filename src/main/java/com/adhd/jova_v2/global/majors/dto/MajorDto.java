package com.adhd.jova_v2.global.majors.dto;

import com.adhd.jova_v2.global.majors.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MajorDto {
    private Long id;
    private String name;

    public static MajorDto fromEntity(Major major) {
        return MajorDto.builder()
                .id(major.getId())
                .name(major.getName())
                .build();
    }

    public Major toEntity() {
        return Major.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}