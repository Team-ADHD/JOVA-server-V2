package com.adhd.jova_v2.domain.auth.exception;

public class TokenBlacklistedException extends RuntimeException {
    public TokenBlacklistedException(String message) {
        super(message);
    }
}