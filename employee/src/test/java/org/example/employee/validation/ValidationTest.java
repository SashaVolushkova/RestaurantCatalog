package org.example.employee.validation;

import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.error.ValidationException;
import org.example.employee.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class ValidationTest {
    private static final String BASE_PATH = "json/";
    private static final String EMPLOYEE_PATH = BASE_PATH + "employee.json";

    @Test
    void validationEmployeeUpdateSuccessTest() throws IOException {
        final EmployeeRequestDTO request =
                TestUtil.readJsonResource(EMPLOYEE_PATH, EmployeeRequestDTO.class);
        List<ValidationException> validationExceptions = ValidationUtils.validateUpdate(request);
        Assertions.assertEquals(0, validationExceptions.size());
    }

    @Test
    void validateEmployeeUpdateExceptionTest() throws IOException {
        final EmployeeRequestDTO request =
                TestUtil.readJsonResource(EMPLOYEE_PATH, EmployeeRequestDTO.class);
        request.setId(null);
        request.setName("Name");
        request.setSalary(null);
        request.setEmail("test.com");
        List<ValidationException> validationExceptions = ValidationUtils.validateUpdate(request);
        Assertions.assertEquals(4, validationExceptions.size());
    }

    @Test
    void validateEmployeeCreateExceptionTest() throws IOException {
        final EmployeeRequestDTO request =
                TestUtil.readJsonResource(EMPLOYEE_PATH, EmployeeRequestDTO.class);
        List<ValidationException> validationExceptions = ValidationUtils.validateCreate(request);
        Assertions.assertEquals(1, validationExceptions.size());
    }
}
