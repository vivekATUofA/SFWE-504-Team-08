package com.ua.sams.service;

import com.ua.sams.entity.Application; // <-- NEW IMPORT
import com.ua.sams.entity.Scholarship;
import com.ua.sams.entity.User; // <-- NEW IMPORT
import com.ua.sams.repository.ApplicationRepository; // <-- NEW IMPORT
import com.ua.sams.repository.ScholarshipRepository;
import com.ua.sams.repository.UserRepository; // <-- NEW IMPORT
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepo; // Renamed repo for clarity
    private final UserRepository userRepo; // <-- NEW DEPENDENCY
    private final ApplicationRepository applicationRepo; // <-- NEW DEPENDENCY

    public ScholarshipService(
            ScholarshipRepository scholarshipRepo,
            UserRepository userRepo,
            ApplicationRepository applicationRepo) {

        this.scholarshipRepo = scholarshipRepo;
        this.userRepo = userRepo;
        this.applicationRepo = applicationRepo;
    }

    // --- Existing CRUD Methods ---

    public List<Scholarship> getAll() {
        return scholarshipRepo.findAll();
    }

    public Optional<Scholarship> getById(Long id) {
        return scholarshipRepo.findById(id);
    }

    public Scholarship create(Scholarship s) {
        return scholarshipRepo.save(s);
    }

    public Scholarship update(Long id, Scholarship s) {
        s.setId(id);
        return scholarshipRepo.save(s);
    }

    public void delete(Long id) {
        scholarshipRepo.deleteById(id);
    }

    // --- NEW Application Logic Methods (to fix Compilation Errors) ---

    // ERROR FIX 1: Implements the method used in ScholarshipController
    public void applyForScholarship(Long scholarshipId, Long userId) {

        // 1. Find Scholarship (or throw exception)
        Scholarship scholarship = scholarshipRepo.findById(scholarshipId)
                .orElseThrow(() -> new RuntimeException("Scholarship not found with ID: " + scholarshipId));

        // 2. Find User (or throw exception)
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // 3. Create and save the new Application
        // NOTE: You must have a suitable constructor in your Application entity
        // that accepts Scholarship and User.
        Application application = new Application(scholarship, user);
        applicationRepo.save(application);
    }

    // ERROR FIX 2: Implements the method used in ScholarshipController
    public List<Application> getApplicationsByScholarship(Long scholarshipId) {
        return applicationRepo.findByScholarshipId(scholarshipId);
    }
}