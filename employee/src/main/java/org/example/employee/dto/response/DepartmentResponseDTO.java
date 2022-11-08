package org.example.employee.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private DepartmentResponseDTO parentDepartment;
    private EmployeeResponseDTO chief;
}
