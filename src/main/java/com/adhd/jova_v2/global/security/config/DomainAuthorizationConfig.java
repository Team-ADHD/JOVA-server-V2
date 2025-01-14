package com.adhd.jova_v2.global.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class DomainAuthorizationConfig {
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v2/users/**", "/api/v2/auth/**").permitAll()
                .requestMatchers("/api/v2/**").hasAnyRole("USER", "ADMIN", "DEVELOPER")
                .anyRequest().authenticated()
        );
    }
}