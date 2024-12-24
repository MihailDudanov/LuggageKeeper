package com.example.luggagekeeper.services;

import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.enumerations.Role;

public interface AuthService {

    Boolean checkPassword(User user, String password);
    User login(String username, String password);
}
