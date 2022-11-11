package org.example.employee.service.impl;

import org.example.employee.model.enities.EmployeeEntity;
import org.example.employee.service.DepartmentService;
import org.example.employee.service.EmployeeService;
import org.example.employee.service.ReportService;
import org.example.employee.service.util.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    @Autowired
    public ReportServiceImpl(EmployeeService employeeService, DepartmentService departmentService,
                             SalaryService salaryService) {
        this.employeeService = employeeService;
        this.salaryService = salaryService;
    }

    @Override
    public BigDecimal getSummarySalaryReport() {
        List<EmployeeEntity> employees = employeeService.getEmployees();
        return getSummary(employees);
    }

    @Override
    public BigDecimal getSummarySalaryReportByDepartmentId(Long departmentId) {
        List<EmployeeEntity> employees = employeeService.getEmployeesByDepartmentId(departmentId);
        return getSummary(employees);
    }

    private BigDecimal getSummary(List<EmployeeEntity> employees) {
        BigDecimal summary = new BigDecimal(0);
        for (var e : employees) {
            summary = summary.add(salaryService.calculateSalary(e.getType(), e.getBaseSalary()));
        }

        return summary.setScale(2, RoundingMode.HALF_UP);
    }
}

