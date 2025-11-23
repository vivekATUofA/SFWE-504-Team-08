package com.ua.sams.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;

    private LocalDateTime applicationDate;

    // Constructors
    public Application() {
        this.applicationDate = LocalDateTime.now();
    }

    // The constructor required by ScholarshipService to create a new application
    public Application(Scholarship scholarship, User user) {
        this.scholarship = scholarship;
        this.user = user;
        this.applicationDate = LocalDateTime.now();
    }

    // Getters and Setters (omitted for brevity, but required in a real project)

    // Example Getter for reference
    public Long getId() { return id; }
    // ...
}