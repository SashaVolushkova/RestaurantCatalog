package org.example.employee.dto.request;

import lombok.Data;
import org.example.employee.validation.ValidationGroups;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
public class DepartmentRequestDTO {

    @NotNull(groups = ValidationGroups.UpdateInfo.class)
    @Null(groups = ValidationGroups.CreateInfo.class)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[а-яА-ЯёЁ ]+")
    private String name;

    private Long parentDepartmentId;

    private Long chiefId;
}
