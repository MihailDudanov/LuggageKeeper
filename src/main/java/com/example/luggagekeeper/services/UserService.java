package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User register(String username, String email, String password, Role role);
}
