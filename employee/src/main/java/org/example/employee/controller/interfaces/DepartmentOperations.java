package org.example.employee.controller.interfaces;

import org.example.employee.model.dto.request.DepartmentRequestDTO;
import org.example.employee.model.dto.response.DepartmentResponseDTO;
import org.example.employee.validation.ValidationGroups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RequestMapping
public interface DepartmentOperations {
    @GetMapping("/{id}")
    DepartmentResponseDTO getDepartmentById(@PathVariable("id") Long id);

    @GetMapping
    List<DepartmentResponseDTO> getDepartments();

    @PostMapping(produces = "application/json;charset=UTF-8")
    DepartmentResponseDTO createDepartment(
            @Validated({Default.class, ValidationGroups.CreateInfo.class}) @RequestBody DepartmentRequestDTO request);

    @PutMapping(produces = "application/json;charset=UTF-8")
    DepartmentResponseDTO updateDepartment(
            @Validated({Default.class, ValidationGroups.UpdateInfo.class}) @RequestBody DepartmentRequestDTO requestDTO);

    @DeleteMapping("/{id}")
    void deleteDepartment(@PathVariable("id") Long id);
}
