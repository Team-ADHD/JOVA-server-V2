package com.adhd.jova_v2.domain.auth.exception;

public class SignInFailedException extends RuntimeException {
    public SignInFailedException(String message) {
        super(message);
    }
}