package com.example.luggagekeeper.services.implementation;

import com.example.luggagekeeper.exceptions.InvalidUsernameOrPasswordException;
import com.example.luggagekeeper.exceptions.PasswordsDoNotMatchException;
import com.example.luggagekeeper.exceptions.UsernameAlreadyExistsException;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.enumerations.Role;
import com.example.luggagekeeper.repository.UserRepository;
import com.example.luggagekeeper.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(String username, String email, String password, Role role) {
       if( username ==null || username.isEmpty() || password == null || password.isEmpty())
           throw new InvalidUsernameOrPasswordException();


        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

       User user= new User(username, email,passwordEncoder.encode(password),role);
        return userRepository.save(user);
    }
}
