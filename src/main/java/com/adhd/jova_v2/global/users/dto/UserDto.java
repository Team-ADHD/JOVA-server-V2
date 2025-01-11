package com.adhd.jova_v2.global.users.dto;

import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.users.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private UserRole role;
    private int grade;
    private int classNum;
    private int generation;
    private String profilePictureUri;
    private boolean banned;

    public User toEntity() {
        return User.builder()
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