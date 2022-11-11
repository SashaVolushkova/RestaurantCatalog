package org.example.employee.service;

import java.math.BigDecimal;

public interface ReportService {
    BigDecimal getSummarySalaryReport();
    BigDecimal getSummarySalaryReportByDepartmentId(Long departmentId);
}
