package com.adhd.jova_v2.global.security.jwt.filter;

import com.adhd.jova_v2.global.exception.presentation.dto.response.ErrorResponse;
import com.adhd.jova_v2.global.exception.presentation.enums.ErrorType;
import com.adhd.jova_v2.global.security.enums.role.UserRole;
import com.adhd.jova_v2.global.security.jwt.service.JwtParserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final JwtParserService jwtParserService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/api/v2/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith(BEARER_PREFIX)) {
            setErrorResponse(response, "Missing or invalid Authorization header");
            return;
        }
        String token = bearerToken.substring(BEARER_PREFIX.length());
        try {
            if (!jwtParserService.validateToken(token)) {
                throw new RuntimeException("Access Token is invalid or expired");
            }
            String userUuid = jwtParserService.extractUuid(token).toString();
            UserRole userRole = jwtParserService.extractUserRole(token);
            List<GrantedAuthority> authorities =  Collections.singletonList(new SimpleGrantedAuthority(userRole.name()));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userUuid, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            setErrorResponse(response, "Authentication error: " + e.getMessage());
        }
    }

    private void setErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse(ErrorType.CLIENT_ERROR, errorMessage, HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}