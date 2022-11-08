package org.example.employee.dto.request;

import lombok.Data;
import org.example.employee.enums.EmployeeType;
import org.example.employee.validation.ValidationGroups;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class EmployeeRequestDTO {
    @NotNull(groups = ValidationGroups.UpdateInfo.class)
    @Null(groups = ValidationGroups.CreateInfo.class)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[а-яА-ЯёЁ ]+")
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long departmentId;

    @NotNull(groups = ValidationGroups.UpdateInfo.class)
    @Null(groups = ValidationGroups.CreateInfo.class)
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal salary;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal baseSalary;

    @NotNull
    private EmployeeType type;
}
