package org.example.employee.error;

import java.util.List;
import java.util.stream.Collectors;

public class MultiValidationException extends RuntimeException{

    private static final String EXCEPTION_DELIMITER = ";\n";

    public MultiValidationException(List<ValidationException> validationExceptions) {
        super(
                validationExceptions.stream()
                        .map(Throwable::getMessage)
                        .collect(Collectors.joining(EXCEPTION_DELIMITER))
        );
    }
}
