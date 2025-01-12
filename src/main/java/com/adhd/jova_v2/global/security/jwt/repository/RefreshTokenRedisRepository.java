package com.adhd.jova_v2.global.security.jwt.repository;

import com.adhd.jova_v2.global.security.jwt.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}