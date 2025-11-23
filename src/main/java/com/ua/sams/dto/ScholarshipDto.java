package com.ua.sams.dto;

import lombok.Data;

@Data
public class ScholarshipDto {
    private Long id;
    private String name;
    private String description;
    private Double amount;
    private boolean archived;
}
