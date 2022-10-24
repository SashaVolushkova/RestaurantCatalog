package org.example.employee.service.impl;

import lombok.AllArgsConstructor;
import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.error.MultiValidationException;
import org.example.employee.error.NotFoundRecordException;
import org.example.employee.error.ValidationException;
import org.example.employee.mapper.EmployeeMapper;
import org.example.employee.repository.EmployeeRepository;
import org.example.employee.service.EmployeeService;
import org.example.employee.validation.ValidationUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_TABLE = "employee";

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

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
        List<ValidationException> validationExceptions = ValidationUtils.validateCreate(request);
        if (!validationExceptions.isEmpty()) throw new MultiValidationException(validationExceptions);

        return Optional.of(request)
                .map(mapper::toEntity)
                .map(employeeRepository::save)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO request) {
        List<ValidationException> validationExceptions = ValidationUtils.validateUpdate(request);
        if (!validationExceptions.isEmpty()) throw new MultiValidationException(validationExceptions);

        return Optional.of(request)
                .map(mapper::toEntity)
                .map(employeeRepository::save)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .ifPresentOrElse(employeeRepository::delete,
                        () -> {
                            throw new NotFoundRecordException(new Object[]{EMPLOYEE_TABLE, id.toString()});
                        });
    }
}
