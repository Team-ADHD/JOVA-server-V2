package com.adhd.jova_v2.domain.auth.service;

import com.adhd.jova_v2.domain.auth.presentation.dto.request.SignInRequest;
import com.adhd.jova_v2.domain.auth.presentation.dto.response.TokenResponse;

public interface SignInService {
    TokenResponse execute(SignInRequest request);
}
