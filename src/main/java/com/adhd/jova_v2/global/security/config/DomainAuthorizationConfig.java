package com.adhd.jova_v2.global.security.config;

import com.adhd.jova_v2.global.security.enums.role.UserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class DomainAuthorizationConfig {

    private static final String API_V2_PATH = "/api/v2";
    private static final String USERS_PATH = API_V2_PATH + "/users";
    private static final String AUTH_PATH = API_V2_PATH + "/auth";
    private static final String JOBS_PATH = API_V2_PATH + "/jobs";
    private static final String PROFILE_PATH = API_V2_PATH + "/profile";

    private final Environment environment;

    public DomainAuthorizationConfig(Environment environment) {
        this.environment = environment;
    }

    public void configure(HttpSecurity http) throws Exception {
        if (isDevEnvironment()) {
            http.authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
            );
        } else {
            http.authorizeHttpRequests(auth -> auth
                    .requestMatchers(USERS_PATH + "/password",
                            USERS_PATH + "/password/email",
                            USERS_PATH + "/password/email/verify",
                            USERS_PATH + "/{userId}",
                            JOBS_PATH,
                            JOBS_PATH + "/{jobId}",
                            JOBS_PATH + "/search",
                            JOBS_PATH + "/{jobId}/applications",
                            JOBS_PATH + "/{jobId}/applications/{userId}/status",
                            JOBS_PATH + "/user/current/jobs",
                            JOBS_PATH + "/user/current/applications",
                            JOBS_PATH + "/user/jobs",
                            JOBS_PATH + "/user/applications",
                            PROFILE_PATH,
                            PROFILE_PATH + "/{userId}",
                            AUTH_PATH + "/signin",
                            AUTH_PATH + "/refresh"
                    ).permitAll()
                    .requestMatchers(API_V2_PATH + "/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name(), UserRole.DEVELOPER.name())
                    .anyRequest().denyAll()
            );
        }
    }

    private boolean isDevEnvironment() {
        return environment.matchesProfiles("dev");
    }
}