package com.aiplatform.tracking_service.dto;

import lombok.Data;

@Data
public class DietSummary {
    private int todayCalories;
    private int goal;
    private int entriesCount;
}
