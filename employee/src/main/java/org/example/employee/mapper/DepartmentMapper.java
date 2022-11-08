package org.example.employee.mapper;

import org.example.employee.dto.request.DepartmentRequestDTO;
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
public interface DepartmentMapper {

    @Mapping(target = "chief", ignore = true)
    @Mapping(target = "parentId", source = "parentDepartment.id")
    DepartmentResponseDTO toDTO(DepartmentEntity entity);

    @AfterMapping
    default void mapChief(DepartmentEntity entity, @MappingTarget DepartmentResponseDTO responseDTO) {
        responseDTO.setChief(
                Optional.ofNullable(entity)
                        .map(DepartmentEntity::getChief)
                        .map(x -> new EmployeeResponseDTO(x.getId(), x.getName(), x.getEmail(), null, null))
                        .orElse(null)
        );
    }

    default DepartmentEntity toEntity(DepartmentRequestDTO request) {
        if (request == null) {
            return null;
        }

        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setParentDepartment(Optional.ofNullable(request.getParentDepartmentId())
                .map(x -> DepartmentEntity.builder().id(x).build())
                .orElse(null)
        );
        entity.setChief(Optional.ofNullable(request.getChiefId())
                .map(x -> EmployeeEntity.builder().id(x).build())
                .orElse(null)
        );

        return entity;
    }
}
