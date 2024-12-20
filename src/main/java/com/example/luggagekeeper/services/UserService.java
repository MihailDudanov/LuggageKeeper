package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String email, String password, String repeatPassword, String name, String surname, Role role);
}
