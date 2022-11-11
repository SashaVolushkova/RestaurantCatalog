package org.example.user.service;

import org.example.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<User> getUsers(Pageable pageable);
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
