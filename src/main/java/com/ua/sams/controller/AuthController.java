package com.ua.sams.controller;

import com.ua.sams.dto.LoginRequest;
import com.ua.sams.dto.RegisterRequest;
import com.ua.sams.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest req) {
        return authService.register(req.getFullName(), req.getEmail(), req.getPassword());
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest req) {
        return authService.login(req.getEmail(), req.getPassword());
    }
}
