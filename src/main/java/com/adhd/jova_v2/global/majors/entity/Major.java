package com.adhd.jova_v2.global.majors.entity;

import com.adhd.jova_v2.global.majors.dto.MajorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "majors")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;

    public MajorDto toDto() {
        return MajorDto.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}