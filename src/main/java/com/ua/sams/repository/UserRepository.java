package com.ua.sams.repository;

import com.ua.sams.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Inside UserRepository.java
public interface UserRepository extends JpaRepository<User, Long> {

    // Existing method
    Optional<User> findByEmail(String email);

    // NEW FIX: This method is needed by DataLoader and AuthService
    boolean existsByEmail(String email);
}