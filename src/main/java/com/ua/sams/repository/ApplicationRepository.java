package com.ua.sams.repository;

import com.ua.sams.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Custom method signature needed by ScholarshipService for the GET endpoint
    List<Application> findByScholarshipId(Long scholarshipId);
}