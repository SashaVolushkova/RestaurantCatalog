package org.example.employee.controller.implementations;

import org.example.employee.controller.interfaces.ReportOperations;
import org.example.employee.service.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/report")
public class ReportController implements ReportOperations {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public BigDecimal getSummarySalaryReport() {
        return reportService.getSummarySalaryReport();
    }

    @Override
    public BigDecimal getSummarySalaryReportByDepartmentId(Long departmentId) {
        return reportService.getSummarySalaryReportByDepartmentId(departmentId);
    }
}
