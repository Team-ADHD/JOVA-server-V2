package com.adhd.jova_v2.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}