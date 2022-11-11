package org.example.employee.controller.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@RequestMapping
public interface ReportOperations {
    @GetMapping("/salary")
    BigDecimal getSummarySalaryReport();

    @GetMapping("/salary/{id}")
    BigDecimal getSummarySalaryReportByDepartmentId(@PathVariable("id") Long departmentId);
}
