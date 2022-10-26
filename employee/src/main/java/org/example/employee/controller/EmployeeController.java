package org.example.employee.controller;

import lombok.AllArgsConstructor;
import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.service.EmployeeService;
import org.example.employee.validation.ValidationGroups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public EmployeeResponseDTO createEmployee(
            @Validated({Default.class,ValidationGroups.CreateInfo.class}) @RequestBody EmployeeRequestDTO request) {
        return employeeService.createEmployee(request);
    }

    @PutMapping(produces = "application/json;charset=UTF-8")
    public EmployeeResponseDTO updateEmployee(
            @Validated({Default.class, ValidationGroups.UpdateInfo.class}) @RequestBody EmployeeRequestDTO request) {
        return employeeService.updateEmployee(request);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
