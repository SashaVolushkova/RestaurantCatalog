package org.example.user.repository;

import liquibase.pro.packaged.Q;
import org.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u")
    Set<User> getUsers();

    Optional<User> findById(Long id);
    Set<User> findByNicknameOrEmail(String nickname, String email);
}
