package com.aiplatform.tracking_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

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
    private Long foodId;
    private String userEmail;
    private String foodName;
    private Double fat;
    private Double servingSize;
    private Double numberOfServings;
    private Double calories;
    private Double protein;
    private Double carbs;
    private String mealType;
    private LocalDateTime timestamp;
}