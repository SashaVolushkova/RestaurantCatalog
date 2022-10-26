package org.example.employee.error;

public class NotFoundRecordException extends RuntimeException{

    public NotFoundRecordException(Object[] args) {
        super(String.format(ErrorCode.NOT_FOUND_RECORD.getErrorDescription(), args));
    }
}
