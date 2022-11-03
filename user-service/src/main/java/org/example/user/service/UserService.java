package org.example.user.service;

import org.example.user.model.User;

import java.util.Set;

public interface UserService {
    public Set<User> getUsers();
    public User getUserById(Long id);
    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long id);
}
