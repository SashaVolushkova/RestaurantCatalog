package org.example.employee.controller.interfaces;

import org.example.employee.model.dto.request.EmployeeRequestDTO;
import org.example.employee.model.dto.response.EmployeeResponseDTO;
import org.example.employee.validation.ValidationGroups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RequestMapping
public interface EmployeeOperations {
    @GetMapping("/{id}")
    EmployeeResponseDTO getEmployeeById(@PathVariable("id") Long id);

    @GetMapping
    List<EmployeeResponseDTO> getEmployees();

    @PostMapping(produces = "application/json;charset=UTF-8")
    EmployeeResponseDTO createEmployee(
            @Validated({Default.class, ValidationGroups.CreateInfo.class}) @RequestBody EmployeeRequestDTO request);

    @PutMapping(produces = "application/json;charset=UTF-8")
    EmployeeResponseDTO updateEmployee(
            @Validated({Default.class, ValidationGroups.UpdateInfo.class}) @RequestBody EmployeeRequestDTO request);

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable("id") Long id);
}
