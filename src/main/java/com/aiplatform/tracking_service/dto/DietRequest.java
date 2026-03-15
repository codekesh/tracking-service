package com.aiplatform.tracking_service.dto;

import lombok.Data;

@Data
public class DietRequest {
    private String foodName;
    private Integer calories;
    private Double protein;
    private Double carbs;
    private Double fat;
}
