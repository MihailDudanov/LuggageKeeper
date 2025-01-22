package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.exceptions.InvalidUserCredentialsException;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.repository.UserRepository;
import com.example.luggagekeeper.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImplementation(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

@Override
    public Boolean checkPassword(User user,String password) {
        if(user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUserCredentialsException();
        }
        User user = userRepository.findByUsername(username).orElseThrow(InvalidUserCredentialsException::new);
        if(passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new InvalidUserCredentialsException();
        }
    }

}
