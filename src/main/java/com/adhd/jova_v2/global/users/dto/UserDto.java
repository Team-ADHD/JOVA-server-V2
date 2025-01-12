package com.adhd.jova_v2.global.users.dto;

import com.adhd.jova_v2.global.jobs.dto.JobDto;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.users.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private UserRole role;
    private Integer grade;
    private Integer classNum;
    private Integer generation;
    private String profilePictureUri;
    private Boolean banned;
    private List<JobDto> jobs;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .grade(user.getGrade())
                .classNum(user.getClassNum())
                .generation(user.getGeneration())
                .profilePictureUri(user.getProfilePictureUri())
                .banned(user.getBanned())
                .jobs(user.getJobs() != null
                        ? user.getJobs().stream().map(JobDto::fromEntity).toList()
                        : null)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .grade(this.grade)
                .classNum(this.classNum)
                .generation(this.generation)
                .profilePictureUri(this.profilePictureUri)
                .banned(this.banned)
                .build();
    }
}