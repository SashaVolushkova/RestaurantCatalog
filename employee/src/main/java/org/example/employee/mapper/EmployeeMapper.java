package org.example.employee.mapper;


import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.dto.response.DepartmentResponseDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.model.DepartmentEntity;
import org.example.employee.model.EmployeeEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "departmentId", source = "department.id")
    EmployeeResponseDTO toDTO(EmployeeEntity entity);

    @Mapping(target = "department", ignore = true)
    EmployeeEntity toEntity(EmployeeRequestDTO request);

    @AfterMapping
    default void mapDepartment(EmployeeEntity entity, @MappingTarget EmployeeResponseDTO responseDTO) {
        responseDTO.setDepartmentId(Optional.ofNullable(entity)
                .map(EmployeeEntity::getDepartment)
                .map(DepartmentEntity::getId)
                .orElse(null)
        );
    }
}
