package com.adhd.jova_v2.global.users.entity;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.users.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", length = 50, nullable = false, unique = true)
    @Email(message = "Invalid email")
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    @JsonIgnore
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "grade", nullable = false)
    @Min(value = 1, message = "Grade must be between 1 and 3")
    @Max(value = 3, message = "Grade must be between 1 and 3")
    private Integer grade;
    @Column(name = "class_num", nullable = false)
    @Min(value = 1, message = "Class number must be between 1 and 4")
    @Max(value = 4, message = "Class number must be between 1 and 4")
    private Integer classNum;
    @Column(name = "generation", nullable = false)
    @Min(value = 1, message = "Generation must be between 1 and 9")
    @Max(value = 9, message = "Generation must be between 1 and 9")
    private Integer generation;
    @Column(name = "profile_picture_uri", length = 100)
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL")
    private String profilePictureUri;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> jobs;
    @Column(name = "banned", nullable = false)
    private Boolean banned = false;

    public void addJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null");
        }
        this.jobs.add(job);
        job.setUser(this);
    }

    public void removeJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null");
        }
        this.jobs.remove(job);
        job.setUser(null);
    }

    public UserDto toDto() {
        return UserDto.builder()
                .id(this.id)
                .email(this.email)
                .role(this.role)
                .grade(this.grade)
                .classNum(this.classNum)
                .generation(this.generation)
                .profilePictureUri(this.profilePictureUri)
                .banned(this.banned)
                .build();
    }
}