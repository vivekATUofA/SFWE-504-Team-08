package com.ua.sams.controller;

import com.ua.sams.entity.Scholarship;
import com.ua.sams.service.ScholarshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // NEW
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
@CrossOrigin
public class ScholarshipController {

    private final ScholarshipService svc;

    public ScholarshipController(ScholarshipService svc) {
        this.svc = svc;
    }

    // All authenticated users (ROLE_USER or ROLE_ADMIN) can view
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Scholarship> getAll() {
        return svc.getAll();
    }

    // All authenticated users can view a single scholarship
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Scholarship> getOne(@PathVariable Long id) {
        return svc.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Only ADMINs can create a scholarship
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Scholarship create(@RequestBody Scholarship s) {
        return svc.create(s);
    }

    // Only ADMINs can update a scholarship
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Scholarship> update(@PathVariable Long id, @RequestBody Scholarship s) {
        if (svc.getById(id).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(svc.update(id, s));
    }

    // Only ADMINs can delete a scholarship
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }


    // All authenticated users can apply for a scholarship
    @PostMapping("/{scholarshipId}/apply/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> applyForScholarship(
            @PathVariable Long scholarshipId,
            @PathVariable Long userId) {

        svc.applyForScholarship(scholarshipId, userId);
        return ResponseEntity.ok().build();
    }

    // You should also add an endpoint to view applications for an ADMIN
    @GetMapping("/{scholarshipId}/applications")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getApplicationsByScholarship(@PathVariable Long scholarshipId) {
        // You'll need a method in your service to handle this logic
        List<?> applications = svc.getApplicationsByScholarship(scholarshipId);
        return ResponseEntity.ok(applications);
    }
}