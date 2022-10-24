package org.example.employee.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    MISSING_REQUIRED_ATTRIBUTE("Отсутствует обязательный атрибут: %s"),
    NOT_NULL_ATTRIBUTE("В представлении лишний атрибут: %s"),
    INVALID_ATTRIBUTE_FORM("Неверный формат атрибута: %s"),
    NOT_FOUND_RECORD("В таблице: %s не найнеда запись с идентификатором: %s");

    private final String errorDescription;
}
