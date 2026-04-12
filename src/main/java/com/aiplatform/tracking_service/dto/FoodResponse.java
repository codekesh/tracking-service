package com.aiplatform.tracking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodResponse {
    private Long id;
    private String name;
    private String category;
    private Double servingSize;
    private String servingUnit;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private Boolean active;
}