package org.example.employee.service.impl;

import lombok.AllArgsConstructor;
import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.error.NotFoundRecordException;
import org.example.employee.mapper.EmployeeMapper;
import org.example.employee.model.DepartmentEntity;
import org.example.employee.model.EmployeeEntity;
import org.example.employee.repository.DepartmentRepository;
import org.example.employee.repository.EmployeeRepository;
import org.example.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final String DEPARTMENT_TABLE = "department";
    private static final String EMPLOYEE_TABLE = "employee";

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper mapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        return Optional.of(id)
                .flatMap(employeeRepository::getEmployeeById)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFoundRecordException(new Object[]{EMPLOYEE_TABLE, id.toString()}));
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        return employeeRepository.getEmployees().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {
        EmployeeEntity e = mapper.toEntity(request);

        Optional<DepartmentEntity> department = departmentRepository.findById(request.getDepartmentId());
        if (department.isEmpty()) {
            throw new NotFoundRecordException(new Object[]{DEPARTMENT_TABLE, request.getDepartmentId().toString()});
        }
        e.setDepartment(department.get());

        return Optional.of(employeeRepository.save(e))
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO request) {
        EmployeeEntity updateEmployee = mapper.toEntity(request);

        Optional<DepartmentEntity> department = departmentRepository.findById(request.getDepartmentId());
        if (department.isEmpty()) {
            throw new NotFoundRecordException(new Object[]{DEPARTMENT_TABLE, request.getDepartmentId().toString()});
        }
        updateEmployee.setDepartment(department.get());

        Optional<EmployeeEntity> employeeOp = employeeRepository.findById(request.getId());
        if (employeeOp.isEmpty()) {
            throw new NotFoundRecordException(new Object[]{EMPLOYEE_TABLE, request.getId().toString()});
        }

        EmployeeEntity employeeEntity = employeeOp.get();
        employeeEntity.setDepartment(updateEmployee.getDepartment());
        employeeEntity.setEmail(updateEmployee.getEmail());
        employeeEntity.setName(updateEmployee.getName());
        employeeEntity.setBaseSalary(updateEmployee.getBaseSalary());
        return mapper.toDTO(employeeEntity);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .ifPresentOrElse(e -> {
                            departmentRepository.removeChief(id);
                            employeeRepository.deleteById(id);
                        },
                        () -> {
                            throw new NotFoundRecordException(new Object[]{EMPLOYEE_TABLE, id.toString()});
                        });
    }
}
