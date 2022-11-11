package org.example.employee.service.util;

import org.example.employee.model.enums.EmployeeType;

import java.math.BigDecimal;

public interface SalaryService {
    BigDecimal calculateSalary(EmployeeType type, BigDecimal baseSalary);
}
