package com.adhd.jova_v2.domain.auth.presentation.controller;

import com.adhd.jova_v2.domain.auth.presentation.dto.request.RefreshRequest;
import com.adhd.jova_v2.domain.auth.presentation.dto.request.SignInRequest;
import com.adhd.jova_v2.domain.auth.presentation.dto.response.TokenResponse;
import com.adhd.jova_v2.domain.auth.service.RefreshService;
import com.adhd.jova_v2.domain.auth.service.SignInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {

    private final SignInService signInService;
    private final RefreshService refreshService;

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(signInService.execute(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody RefreshRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(refreshService.execute(request.getRefreshToken()));
    }
}