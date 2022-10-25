package org.example.employee.validation;

import org.example.employee.error.ErrorCode;
import org.example.employee.error.ValidationException;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ValidationUtils {

    private static final String NOT_NULL = "{javax.validation.constraints.NotNull.message}";
    private static final String NULL = "{javax.validation.constraints.Null.message}";
    private static final String FIELD_DELIMITER = " - ";

    private static final Validator VALIDATOR = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("validationMessages")))
            .buildValidatorFactory()
            .getValidator();

    public static <T> List<ValidationException> validateCreate(T value) {
        return validate(value, Default.class, ValidationGroups.CreateInfo.class);
    }

    public static <T> List<ValidationException> validateUpdate(T value) {
        return validate(value, Default.class, ValidationGroups.UpdateInfo.class);
    }

    public static <T> List<ValidationException> validate(T value, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = getViolations(value, groups);

        if (violations.isEmpty()) return List.of();

        return violations.stream()
                .map(x -> new ValidationException(getErrorCode(Set.of(x)), errorPlaceHolder(createMessage(x))))
                .collect(Collectors.toList());
    }

    private static <T> Set<ConstraintViolation<T>> getViolations(T value, Class<?>... groups) {
        return VALIDATOR.validate(value, groups);
    }

    private static <T> ErrorCode getErrorCode(Set<ConstraintViolation<T>> violations) {
        for (ConstraintViolation<T> violation : violations) {
            if (NOT_NULL.equals(violation.getMessageTemplate())) return ErrorCode.MISSING_REQUIRED_ATTRIBUTE;
            if (NULL.equals(violation.getMessageTemplate())) return ErrorCode.NOT_NULL_ATTRIBUTE;
        }

        return ErrorCode.INVALID_ATTRIBUTE_FORM;
    }

    private static Object[] errorPlaceHolder(String errorFields) {
        return new Object[]{errorFields};
    }

    private static <T> String createMessage(ConstraintViolation<T> violation) {
        return createMessage(violation.getPropertyPath().toString(), violation.getMessage());
    }

    private static String createMessage(String path, String cause) {
        return path + FIELD_DELIMITER + cause;
    }
}
