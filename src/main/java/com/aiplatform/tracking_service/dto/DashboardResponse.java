package com.aiplatform.tracking_service.dto;

import java.util.List;

import lombok.*;

@Data
public class DashboardResponse {

    private UserSummary user;
    private DietSummary diet;
    private StreakSummary streaks;
    private List<String> aiSuggestions;

}
