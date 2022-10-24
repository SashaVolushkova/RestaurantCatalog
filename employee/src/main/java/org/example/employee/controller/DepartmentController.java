package org.example.employee.controller;

import lombok.AllArgsConstructor;
import org.example.employee.dto.request.DepartmentRequestDTO;
import org.example.employee.dto.response.DepartmentResponseDTO;
import org.example.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/departmens")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartmentById(@PathVariable("id") Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping
    public List<DepartmentResponseDTO> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO request) {
        return departmentService.createDepartment(request);
    }

    @PutMapping(produces = "application/json;charset=UTF-8")
    public DepartmentResponseDTO updateDepartment(@RequestBody DepartmentRequestDTO requestDTO) {
        return departmentService.updateDepartment(requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }
}
