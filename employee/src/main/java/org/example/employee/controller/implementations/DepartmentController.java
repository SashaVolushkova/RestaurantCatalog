package org.example.employee.controller.implementations;

import lombok.AllArgsConstructor;
import org.example.employee.controller.interfaces.DepartmentOperations;
import org.example.employee.dto.request.DepartmentRequestDTO;
import org.example.employee.dto.response.DepartmentResponseDTO;
import org.example.employee.service.DepartmentService;
import org.example.employee.validation.ValidationGroups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController implements DepartmentOperations {
    private final DepartmentService departmentService;

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        return departmentService.getDepartmentById(id);
    }

    @Override
    public List<DepartmentResponseDTO> getDepartments() {
        return departmentService.getDepartments();
    }

    @Override
    public DepartmentResponseDTO createDepartment(
            @Validated({Default.class, ValidationGroups.CreateInfo.class}) @RequestBody DepartmentRequestDTO request) {
        return departmentService.createDepartment(request);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(
            @Validated({Default.class, ValidationGroups.UpdateInfo.class}) @RequestBody DepartmentRequestDTO requestDTO) {
        return departmentService.updateDepartment(requestDTO);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentService.deleteDepartment(id);
    }
}
