package org.example.user.service.impl;

import lombok.Data;
import org.example.user.exception.NonUniqueUserException;
import org.example.user.model.User;
import org.example.user.exception.UserNotFoundException;
import org.example.user.repository.UserRepository;
import org.example.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Data
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Set<User> getUsers() {
        return repository.getUsers();
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("Пользователь с таким id не найден");});
    }

    @Override
    public User createUser(User user) {
        Set<User> usersWithSameNickNameOrEmail = repository.findByNicknameOrEmail(user.getNickname(), user.getEmail());
        if (usersWithSameNickNameOrEmail != null && usersWithSameNickNameOrEmail.size() > 0) {
            throw new NonUniqueUserException("Пользователь с таким именем или почтой уже существует");
        }
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = getUserById(user.getId());
        updatedUser.setNickname(user.getNickname());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setInternal(user.isInternal());
        return createUser(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        repository.delete(user);
    }
}
