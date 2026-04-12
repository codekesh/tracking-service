package com.aiplatform.tracking_service.dto;

import lombok.Data;

@Data
public class FoodRequest {
    private String name;
    private String category;
    private Double servingSize;
    private String servingUnit;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;
}