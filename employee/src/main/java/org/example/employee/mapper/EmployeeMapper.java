package org.example.employee.mapper;


import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.dto.response.DepartmentResponseDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.enums.EmployeeType;
import org.example.employee.model.EmployeeEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "salary", expression = "java(baseSalaryToCalculated(entity.getType(), entity.getBaseSalary()))")
    EmployeeResponseDTO toDTO(EmployeeEntity entity);

    @Mapping(source = "departmentId", target = "department.id")
    EmployeeEntity toEntity(EmployeeRequestDTO request);

    @AfterMapping
    default void mapDepartment(EmployeeEntity entity, @MappingTarget EmployeeResponseDTO responseDTO) {
        responseDTO.setDepartment(Optional.ofNullable(entity)
                .map(EmployeeEntity::getDepartment)
                .map(x -> new DepartmentResponseDTO(x.getId(), x.getName(), null, null))
                .orElse(null)
        );
    }

    default BigDecimal baseSalaryToCalculated(EmployeeType type, BigDecimal baseSalary) {
        switch (type) {
            case TESTER -> {
                return baseSalary.divide(new BigDecimal(31), RoundingMode.HALF_DOWN)
                        .multiply(new BigDecimal(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
                                .getDayOfMonth()));
            }
            case MANAGER -> {
                return baseSalary.multiply(new BigDecimal("0.9"));
            }
        }

        return baseSalary;
    }
}
