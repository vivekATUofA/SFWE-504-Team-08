package com.ua.sams.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "scholarships")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double amount;

    private boolean archived = false;
}
