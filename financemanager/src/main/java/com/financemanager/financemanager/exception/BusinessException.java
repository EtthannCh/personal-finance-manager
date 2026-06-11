package com.financemanager.financemanager.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus status;
    public BusinessException(String message, String errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
