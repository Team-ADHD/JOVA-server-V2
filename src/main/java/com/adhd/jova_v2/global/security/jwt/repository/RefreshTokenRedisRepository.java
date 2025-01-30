package com.adhd.jova_v2.global.security.jwt.repository;

import com.adhd.jova_v2.global.security.jwt.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}