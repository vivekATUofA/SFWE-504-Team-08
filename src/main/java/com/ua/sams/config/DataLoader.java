package com.ua.sams.config;

import com.ua.sams.entity.User;
import com.ua.sams.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            String adminEmail = "admin@uasams.local";
            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = new User();
                admin.setFullName("Admin User");
                admin.setEmail(adminEmail);
                admin.setPassword(encoder.encode("password123")); // change later
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("Created admin user: admin@uasams.local / password123");
            }
        };
    }
}
