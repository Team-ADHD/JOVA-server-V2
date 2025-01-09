package com.adhd.jova_v2.global.users.entity;

import com.adhd.jova_v2.global.jobs.entity.Job;
import jakarta.persistence.*;
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
    @Column(name = "email", length = 20)
    private String email;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "class_num")
    private Integer classNum;
    @Column(name = "generation")
    private Integer generation;
    @Column(name = "profile_picture_uri", length = 100)
    private String profilePictureUri;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> jobs;
}