package org.example.user.controller;

import lombok.AllArgsConstructor;
import org.example.user.dto.request.UserCreateRequestDTO;
import org.example.user.dto.request.UserUpdateRequestDTO;
import org.example.user.dto.response.UserResponseDTO;
import org.example.user.mapper.UserMapper;
import org.example.user.model.User;
import org.example.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    private final UserMapper mapper;

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable("id") Long id) {
        User user = service.getUserById(id);
        return mapper.toDTO(user);
    }

    @GetMapping
    public Page<UserResponseDTO> getUsers(@PageableDefault(sort = "nickname") Pageable pageable) {
        Page<User> users = service.getUsers(pageable);
        return users.map(mapper::toDTO);
    }

    @PostMapping
    public UserResponseDTO createUser(
            @RequestBody UserCreateRequestDTO request) {
        User user = service.createUser(mapper.toEntityFromCreateRequestDTO(request));
        return mapper.toDTO(user);
    }

    @PutMapping
    public UserResponseDTO updateUser(
            @RequestBody UserUpdateRequestDTO request) {
        User user = service.updateUser(mapper.toEntityFromUpdateRequestDTO(request));
        return mapper.toDTO(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
    }
}
