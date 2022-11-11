package org.example.employee.error;

import lombok.Getter;

@Getter
public class BusinessRuntimeException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessRuntimeException(ErrorCode errorCode,
                                    Throwable cause, Object...args) {
        super(errorCode.getErrorDescription(args), cause);
        this.errorCode = errorCode;
    }

    public BusinessRuntimeException(ErrorCode errorCode, Object ...args) {
        super(errorCode.getErrorDescription(args));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
