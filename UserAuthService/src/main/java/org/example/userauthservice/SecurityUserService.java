package org.example.userauthservice;

import org.example.userauthservice.model.UsersEntity;
import org.example.userauthservice.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUserService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public SecurityUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> byEmail = usersRepository.findByEmail(username);
        if(byEmail.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserDetails(byEmail.get());
    }
}
