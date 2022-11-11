package org.example.employee.service;

import org.example.employee.model.dto.request.EmployeeRequestDTO;
import org.example.employee.model.dto.response.EmployeeResponseDTO;
import org.example.employee.model.enities.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getEmployeeResponseDTOs();

    List<EmployeeEntity> getEmployees();

    List<EmployeeEntity> getEmployeesByDepartmentId(Long departmentId);

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO request);

    EmployeeResponseDTO updateEmployee(EmployeeRequestDTO request);

    void deleteEmployee(Long id);
}
