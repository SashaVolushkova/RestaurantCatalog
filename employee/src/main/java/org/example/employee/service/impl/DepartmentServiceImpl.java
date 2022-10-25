package org.example.employee.service.impl;

import lombok.AllArgsConstructor;
import org.example.employee.dto.request.DepartmentRequestDTO;
import org.example.employee.dto.response.DepartmentResponseDTO;
import org.example.employee.error.NotFoundRecordException;
import org.example.employee.mapper.DepartmentMapper;
import org.example.employee.repository.DepartmentRepository;
import org.example.employee.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final String DEPARTMENT_TABLE = "department";

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        return Optional.of(id)
                .flatMap(departmentRepository::getDepartmentById)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundRecordException(new Object[]{DEPARTMENT_TABLE, id.toString()}));
    }

    @Override
    public List<DepartmentResponseDTO> getDepartments() {
        return departmentRepository.getDepartments().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request) {
        return Optional.of(request)
                .map(mapper::toEntity)
                .map(departmentRepository::save)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO request) {
        return Optional.of(request)
                .map(mapper::toEntity)
                .map(departmentRepository::save)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        departmentRepository.findById(id)
                .ifPresentOrElse(departmentRepository::delete,
                        () -> {
                            throw new NotFoundRecordException(new Object[]{DEPARTMENT_TABLE, id.toString()});
                        });
    }
}
