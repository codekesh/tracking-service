package com.aiplatform.tracking_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diet_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DietEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String foodName;

    private Integer calories;

    private Double protein;

    private Double carbs;

    private Double fat;

    private LocalDateTime timestamp;
}