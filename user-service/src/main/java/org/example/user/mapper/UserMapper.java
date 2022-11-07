package org.example.user.mapper;

import org.example.user.dto.request.UserCreateRequestDTO;
import org.example.user.dto.request.UserUpdateRequestDTO;
import org.example.user.dto.response.UserResponseDTO;
import org.example.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDTO(User user);
    User toEntityFromCreateRequestDTO(UserCreateRequestDTO requestDto);
    User toEntityFromUpdateRequestDTO(UserUpdateRequestDTO requestDto);
}
