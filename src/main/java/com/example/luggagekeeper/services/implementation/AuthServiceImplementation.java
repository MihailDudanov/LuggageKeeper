package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.exceptions.InvalidUserCredentialsException;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.repository.UserRepository;
import com.example.luggagekeeper.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
