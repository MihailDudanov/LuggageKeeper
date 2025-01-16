package com.example.luggagekeeper.controller;

import com.example.luggagekeeper.exceptions.InvalidUserCredentialsException;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.dto.LoginDTO;
import com.example.luggagekeeper.models.dto.RegisterDTO;
import com.example.luggagekeeper.services.AuthService;
import com.example.luggagekeeper.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/authentication")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            User user = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
            return ResponseEntity.ok("Login successful");
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        if (this.userService.findByUsername(registerDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        this.userService.register(
                registerDTO.getUsername(),
                registerDTO.getEmail(),
                registerDTO.getPassword(),
                registerDTO.getRole()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
