package org.example.user.repository;

import org.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    Set<User> findByNicknameOrEmail(String nickname, String email);
}
