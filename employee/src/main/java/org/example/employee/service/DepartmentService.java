package org.example.employee.service;

import org.example.employee.model.dto.request.DepartmentRequestDTO;
import org.example.employee.model.dto.response.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO getDepartmentById(Long id);

    List<DepartmentResponseDTO> getDepartments();

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO request);

    DepartmentResponseDTO updateDepartment(DepartmentRequestDTO request);

    void deleteDepartment(Long id);
}
