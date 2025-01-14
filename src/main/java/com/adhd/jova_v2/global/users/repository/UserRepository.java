package com.adhd.jova_v2.global.users.repository;

import com.adhd.jova_v2.global.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }