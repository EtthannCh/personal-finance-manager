package com.financemanager.financemanager.exception;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception,
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(exception.getErrorCode());
        error.setMessage(exception.getMessage());
        error.setStatus(exception.getStatus().value());
        error.setTimestamp(Instant.now());
        error.setPath(request.getRequestURI());
        return new ResponseEntity<>(error, exception.getStatus());
    }
}
