package org.example.employee.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_RECORD("В таблице: %s не найдена запись с идентификатором: %s");

    private final String errorDescription;
}
