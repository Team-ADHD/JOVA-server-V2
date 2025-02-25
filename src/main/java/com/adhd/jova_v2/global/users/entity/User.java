package com.adhd.jova_v2.global.users.entity;

import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(name = "uuid", length = 36, nullable = false, unique = true)
    private String UUID;
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
    @Size(min = 1, max = 3, message = "Grade must be between 1 and 3")
    private Integer grade;
    @Column(name = "class_num", nullable = false)
    @Size(min = 1, max = 4, message = "Class number must be between 1 and 4")
    private Integer classNum;
    @Column(name = "generation", nullable = false)
    @Size(min = 1, max = 9, message = "Generation must be between 1 and 9")
    private Integer generation;
    @Column(name = "profile_picture_uri", length = 100)
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL")
    private String profilePictureUri;
    @Column(name = "banned", nullable = false)
    private Boolean banned = false;

    @PrePersist
    private void generateUUID() {
        this.UUID = java.util.UUID.randomUUID().toString();
    }
}