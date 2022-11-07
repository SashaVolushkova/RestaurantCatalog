package org.example.user.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequestDTO {
    @NotNull
    @Size(min = 2, max = 20)
    protected String nickname;
    @NotNull
    @Email(regexp = ".+[@].+[\\.].+")
    protected String email;
    protected boolean internal;
}
