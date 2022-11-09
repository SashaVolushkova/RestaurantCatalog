package org.example.employee.service.impl;

import org.example.employee.enums.EmployeeType;
import org.example.employee.service.SalaryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Value("${application-service-config.project-success}")
    private Double projectSuccess;

    @Override
    public BigDecimal calculateSalary(EmployeeType type, BigDecimal baseSalary) {
        switch (type) {
            case TESTER -> {
                return baseSalary
                        .divide(new BigDecimal(31), RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
                                .getDayOfMonth()))
                        .setScale(2, RoundingMode.HALF_UP);
            }
            case MANAGER -> {
                return baseSalary
                        .multiply(new BigDecimal(projectSuccess))
                        .setScale(2, RoundingMode.HALF_UP);
            }
        }

        return baseSalary;
    }
}
