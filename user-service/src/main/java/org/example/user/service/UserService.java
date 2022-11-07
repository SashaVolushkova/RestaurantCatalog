package org.example.user.service;

import org.example.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface UserService {
    public Page<User> getUsers(Pageable pageable);
    public User getUserById(Long id);
    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long id);
}
