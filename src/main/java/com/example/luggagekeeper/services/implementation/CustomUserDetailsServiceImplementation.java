//package com.example.luggagekeeper.services.implementation;
//
//import com.example.luggagekeeper.services.UserService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsServiceImplementation implements UserDetailsService {
//    private final UserService userService; // Replace with your service that interacts with the database
//
//    public CustomUserDetailsServiceImplementation(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        com.example.luggagekeeper.models.User appUser = userService.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        return User.builder()
//                .username(appUser.getUsername())
//                .password(appUser.getPassword()) // Ensure password is already hashed
//                .roles(appUser.getRole().name()) // Convert roles list to array
//                .build();
//    }
//}
