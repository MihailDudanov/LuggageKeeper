package com.example.luggagekeeper.controller;

import com.example.luggagekeeper.exceptions.InvalidUserCredentialsException;
import com.example.luggagekeeper.models.User;
import com.example.luggagekeeper.models.dto.LoginDTO;
import com.example.luggagekeeper.models.dto.RegisterDTO;
import com.example.luggagekeeper.services.AuthService;
import com.example.luggagekeeper.services.JwtUtil;
import com.example.luggagekeeper.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/authentication")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtUtil jwtUtil; // Inject JWT Service

    public AuthController(AuthService authService, UserService userService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            User user = authService.login(loginDTO.getUsername(), loginDTO.getPassword());

            // Generate JWT Token
            String token = jwtUtil.generateToken(user.getUsername(),user.getRole().name());

            return ResponseEntity.ok(Map.of("token", token));
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        if (userService.findByUsername(registerDTO.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        userService.register(
                registerDTO.getUsername(),
                registerDTO.getEmail(),
                registerDTO.getPassword(),
                registerDTO.getRole()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", ""); // Remove "Bearer " prefix
        String username = jwtUtil.getUsernameFromToken(jwt);

        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // ✅ Return User object
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token"); // ✅ Return error message
        }
    }

}
