package com.taliibHub.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String code;
    private int credits;
    private String description;
    private String teacher;
    private int studentsCount;
    private String days;
    private LocalDateTime nextClassDate;
    private double average;
    private int presence;
    private int progress;

    // Getters and Setters
} 