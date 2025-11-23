package com.ua.sams.service;

import com.ua.sams.config.JwtService;
import com.ua.sams.entity.User;
import com.ua.sams.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final AuthenticationManager authenticationManager; // NEW

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtService jwt, AuthenticationManager authenticationManager) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
        this.authenticationManager = authenticationManager;
    }

    public Map<String, Object> register(String fullName, String email, String password) {
        if (repo.existsByEmail(email))
            throw new RuntimeException("Email already exists");

        User u = new User();
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(encoder.encode(password));
        u.setRole("USER"); // Ensure new users are registered with a role

        repo.save(u);

        return Map.of("message", "Registered successfully");
    }

    public Map<String, Object> login(String email, String password) {
        // Use AuthenticationManager for standard login failure handling
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Principal is a UserDetails object (in our case, the User entity itself)
        User u = (User) authentication.getPrincipal();

        String token = jwt.generateToken(email);

        return Map.of(
                "token", token,
                "user", u
        );
    }
}