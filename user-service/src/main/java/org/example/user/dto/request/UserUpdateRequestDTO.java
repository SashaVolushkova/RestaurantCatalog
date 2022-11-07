package org.example.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequestDTO extends UserCreateRequestDTO {
    @NotNull
    private Long id;
}
