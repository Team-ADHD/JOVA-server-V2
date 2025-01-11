package com.adhd.jova_v2.global.majors.dto;

import com.adhd.jova_v2.global.majors.entity.Major;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
public class MajorDto {
    private Long id;
    private String name;

    public Major toEntity() {
        return Major.builder()
            .name(this.name)
            .build();
    }
}
