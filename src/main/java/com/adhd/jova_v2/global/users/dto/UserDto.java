package com.adhd.jova_v2.global.users.dto;

import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long id;
    private UUID uuid;
    private String email;
    private String password;
    private UserRole role;
    private Integer grade;
    private Integer classNum;
    private Integer generation;
    private String profilePictureUri;
    private Boolean banned;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .uuid(UUID.fromString(user.getUUID()))
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .grade(user.getGrade())
                .classNum(user.getClassNum())
                .generation(user.getGeneration())
                .profilePictureUri(user.getProfilePictureUri())
                .banned(user.getBanned())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .UUID(this.uuid.toString())
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