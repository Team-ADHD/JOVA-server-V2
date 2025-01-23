package com.adhd.jova_v2.global.exception.handler;

import com.adhd.jova_v2.domain.auth.exception.InvalidRefreshTokenException;
import com.adhd.jova_v2.domain.auth.exception.SignInFailedException;
import com.adhd.jova_v2.domain.auth.exception.TokenBlacklistedException;
import com.adhd.jova_v2.global.exception.presentation.dto.response.ErrorResponse;
import com.adhd.jova_v2.global.exception.presentation.enums.ErrorType;
import com.adhd.jova_v2.global.users.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SignInFailedException.class)
    public ResponseEntity<ErrorResponse> handleSignInFailedException(SignInFailedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorType.CLIENT_ERROR,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(TokenBlacklistedException.class)
    public ResponseEntity<ErrorResponse> handleTokenBlacklistedException(TokenBlacklistedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorType.CLIENT_ERROR,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRefreshTokenException(InvalidRefreshTokenException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorType.CLIENT_ERROR,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorType.RESOURCE_ERROR,
                e.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}