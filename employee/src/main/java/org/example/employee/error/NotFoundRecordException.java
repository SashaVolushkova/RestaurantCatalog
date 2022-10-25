package org.example.employee.error;

public class NotFoundRecordException extends ValidationException{

    public NotFoundRecordException(Object[] args) {
        super(ErrorCode.NOT_FOUND_RECORD, args);
    }
}
