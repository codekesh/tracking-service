package com.aiplatform.tracking_service.dto;

import lombok.Data;

@Data
public class DietRequest {
    private String foodName;
    private Long foodId;
    private Double numberOfServings;
    private Double servingSize;
    private String mealType;
}
