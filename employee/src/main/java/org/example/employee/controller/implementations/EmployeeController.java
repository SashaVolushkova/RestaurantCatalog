package org.example.employee.controller.implementations;

import lombok.AllArgsConstructor;
import org.example.employee.controller.interfaces.EmployeeOperations;
import org.example.employee.dto.request.EmployeeRequestDTO;
import org.example.employee.dto.response.EmployeeResponseDTO;
import org.example.employee.service.EmployeeService;
import org.example.employee.validation.ValidationGroups;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController implements EmployeeOperations {

    private final EmployeeService employeeService;

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @Override
    public EmployeeResponseDTO createEmployee(
            @Validated({Default.class,ValidationGroups.CreateInfo.class}) @RequestBody EmployeeRequestDTO request) {
        return employeeService.createEmployee(request);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(
            @Validated({Default.class, ValidationGroups.UpdateInfo.class}) @RequestBody EmployeeRequestDTO request) {
        return employeeService.updateEmployee(request);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }
}
