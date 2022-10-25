package org.example.employee.error;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private final ErrorCode errorCode;

    public ValidationException(ErrorCode errorCode, Object[] args) {
        super(String.format(errorCode.getErrorDescription(), args));
        this.errorCode = errorCode;
    }
}
