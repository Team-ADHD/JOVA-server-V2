package com.adhd.jova_v2.global.users.entity;

import com.adhd.jova_v2.global.jobs.entity.Job;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import jakarta.persistence.*;
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
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "grade", nullable = false)
    private Integer grade;
    @Column(name = "class_num", nullable = false)
    private Integer classNum;
    @Column(name = "generation", nullable = false)
    private Integer generation;
    @Column(name = "profile_picture_uri", length = 100)
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL")
    private String profilePictureUri;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> jobs;
    @Column(name = "banned", nullable = false)
    private Boolean banned = false;
}