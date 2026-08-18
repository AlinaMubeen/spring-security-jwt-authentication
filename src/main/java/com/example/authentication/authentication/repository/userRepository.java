package com.example.authentication.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authentication.authentication.model.User;


public interface userRepository extends JpaRepository<User,Integer> {
Optional<User> getUserByUsername(String username);
}
