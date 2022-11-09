package org.example.employee.service;

import org.example.employee.enums.EmployeeType;

import java.math.BigDecimal;

public interface SalaryService {
    BigDecimal calculateSalary(EmployeeType type, BigDecimal baseSalary);
}
