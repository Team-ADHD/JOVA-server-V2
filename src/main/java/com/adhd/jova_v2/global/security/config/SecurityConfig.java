package com.adhd.jova_v2.global.security.config;

import com.adhd.jova_v2.global.security.jwt.filter.JwtFilter;
import com.adhd.jova_v2.global.security.jwt.service.JwtParserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final DomainAuthorizationConfig domainAuthorizationConfig;
    private final CorsConfig corsConfig;
    private final JwtParserService jwtParserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        domainAuthorizationConfig.configure(http);
        http
                .cors(cors -> cors.configurationSource(corsConfig.configureCors()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(
                        new JwtFilter(jwtParserService),
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(exceptions -> {
                    exceptions.authenticationEntryPoint((request, response, authException) ->
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    );
                    exceptions.accessDeniedHandler((request, response, accessDeniedException) ->
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
                    );
                });
        return http.build();
    }
}