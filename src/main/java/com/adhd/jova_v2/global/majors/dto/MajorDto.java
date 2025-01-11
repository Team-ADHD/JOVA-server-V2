package com.adhd.jova_v2.global.majors.dto;

import com.adhd.jova_v2.global.majors.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MajorDto {
    private Long id;
    private String name;

    /**
     * Converts a Major entity to a MajorDto data transfer object.
     *
     * @param major The Major entity to be converted
     * @return A MajorDto instance with the same id and name as the input Major entity
     */
    public static MajorDto fromEntity(Major major) {
        return MajorDto.builder()
                .id(major.getId())
                .name(major.getName())
                .build();
    }

    /**
     * Converts the current MajorDto instance to a Major entity.
     *
     * @return A Major entity with the same id and name as the current MajorDto
     */
    public Major toEntity() {
        return Major.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
