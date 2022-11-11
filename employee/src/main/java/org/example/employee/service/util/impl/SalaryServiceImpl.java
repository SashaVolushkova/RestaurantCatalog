package org.example.employee.service.util.impl;

import org.example.employee.model.enums.EmployeeType;
import org.example.employee.service.util.SalaryService;
import org.example.employee.service.util.WorkHourService;
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

    private final WorkHourService workHourService;

    public SalaryServiceImpl(WorkHourService workHourService) {
        this.workHourService = workHourService;
    }

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
            case TOP_MANAGER, MANAGER -> {
                return baseSalary
                        .multiply(new BigDecimal(projectSuccess))
                        .setScale(2, RoundingMode.HALF_UP);
            }
            case CLEANER -> {
                return baseSalary
                        .divide(new BigDecimal(8), RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(workHourService.getWorkHours()))
                        .setScale(2, RoundingMode.HALF_UP);
            }
            case INTERN -> {
                return baseSalary
                        .divide(new BigDecimal(2), RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP);
            }
        }

        return baseSalary;
    }
}
