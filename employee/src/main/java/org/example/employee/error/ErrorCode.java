package org.example.employee.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_RECORD("В таблице: %s не найдена запись с идентификатором: %s", HttpStatus.NOT_FOUND),
    WRONG_EMPLOYEE_REQUESTED("Работник с идентификатором:{%s} не относится " +
            "к департаменту с идентификатором:{%s}", HttpStatus.BAD_REQUEST);

    private final String errorDescription;
    private final HttpStatus httpStatus;
    public String getErrorDescription(Object...args) {
        return MessageFormat.format(errorDescription, args);
    }
    public ErrorCode getCode() {
        return this;
    }
}
