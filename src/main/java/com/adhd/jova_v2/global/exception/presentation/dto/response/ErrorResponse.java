package com.adhd.jova_v2.global.exception.presentation.dto.response;

import com.adhd.jova_v2.global.exception.presentation.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    ErrorType errorType;
    String message;
    int code;
}