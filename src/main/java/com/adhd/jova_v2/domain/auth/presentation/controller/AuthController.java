package com.adhd.jova_v2.domain.auth.presentation.controller;

import com.adhd.jova_v2.domain.auth.presentation.dto.response.SignInResponse;
import com.adhd.jova_v2.domain.auth.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {

    private final SignInService signInService;

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn() {
        return ResponseEntity.ok(signInService.execute());
    }
}
