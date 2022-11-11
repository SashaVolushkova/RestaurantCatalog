package org.example.employee.mapper;


import org.example.employee.model.dto.request.EmployeeRequestDTO;
import org.example.employee.model.dto.response.EmployeeResponseDTO;
import org.example.employee.model.enities.DepartmentEntity;
import org.example.employee.model.enities.EmployeeEntity;
import org.example.employee.service.util.SalaryService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    @Autowired
    protected SalaryService salaryService;

    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "salary",
            expression = "java(salaryService.calculateSalary(entity.getType(), entity.getBaseSalary()))")
    public abstract EmployeeResponseDTO toDTO(EmployeeEntity entity);

    @Mapping(target = "department", ignore = true)
    public abstract EmployeeEntity toEntity(EmployeeRequestDTO request);

    @AfterMapping
    public void mapDepartment(EmployeeEntity entity, @MappingTarget EmployeeResponseDTO responseDTO) {
        responseDTO.setDepartmentId(Optional.ofNullable(entity)
                .map(EmployeeEntity::getDepartment)
                .map(DepartmentEntity::getId)
                .orElse(null)
        );
    }
}
