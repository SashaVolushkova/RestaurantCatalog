package org.example.employee.service.impl;

import lombok.AllArgsConstructor;
import org.example.employee.model.dto.request.DepartmentRequestDTO;
import org.example.employee.model.dto.response.DepartmentResponseDTO;
import org.example.employee.error.BusinessRuntimeException;
import org.example.employee.error.ErrorCode;
import org.example.employee.error.NotFoundRecordException;
import org.example.employee.mapper.DepartmentMapper;
import org.example.employee.model.enities.DepartmentEntity;
import org.example.employee.repository.DepartmentRepository;
import org.example.employee.repository.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;
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

        DepartmentEntity parent = getParentDepFromRequest(request);

        DepartmentEntity departmentEntity = mapper.toEntity(request);
        departmentEntity.setParentDepartment(parent);
        return Optional.of(departmentRepository.save(departmentEntity))
                .map(mapper::toDTO)
                .orElse(null);
    }

    private DepartmentEntity getParentDepFromRequest(DepartmentRequestDTO request) {
        if (request.getParentDepartmentId() != null) {

            Optional<DepartmentEntity> parentOp = departmentRepository.findById(request.getParentDepartmentId());
            if (parentOp.isEmpty()) {
                throw new NotFoundRecordException(new Object[]{DEPARTMENT_TABLE, request.getParentDepartmentId().toString()});
            }
            return parentOp.get();
        }
        return null;
    }

    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO request) {
        employeeRepository.getEmployeeById(request.getChiefId()).ifPresent(e -> {
            if (!e.getDepartment().getId().equals(request.getParentDepartmentId())) {
                throw new BusinessRuntimeException(ErrorCode.WRONG_EMPLOYEE_REQUESTED, e.getId(), request.getId());
            }
        });

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
                .ifPresentOrElse(d -> {
                            departmentRepository.removeParent(d.getId());
                            departmentRepository.delete(d);
                        },
                        () -> {
                            throw new NotFoundRecordException(new Object[]{DEPARTMENT_TABLE, id.toString()});
                        });
    }
}
