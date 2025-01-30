package com.adhd.jova_v2.global.scrap.dto;

import com.adhd.jova_v2.global.jobs.dto.JobDto;
import com.adhd.jova_v2.global.scrap.entity.Scrap;
import com.adhd.jova_v2.global.users.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScrapDto {
    private Long id;
    private UserDto user;
    private List<JobDto> scrapJobs;

    public static ScrapDto fromEntity(Scrap scrap) {
        return ScrapDto.builder()
                .id(scrap.getId())
                .user(UserDto.fromEntity(scrap.getUser()))
                .scrapJobs(scrap.getScrapJobs().stream().map(JobDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public Scrap toEntity() {
        return Scrap.builder()
                .id(this.id)
                .user(this.user.toEntity())
                .scrapJobs(this.scrapJobs.stream().map(JobDto::toEntity).collect(Collectors.toList()))
                .build();
    }
}