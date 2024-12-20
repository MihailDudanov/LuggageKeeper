package com.example.luggagekeeper.repository;

import com.example.luggagekeeper.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findByUsername(String username);
}
