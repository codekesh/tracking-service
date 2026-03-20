package com.aiplatform.tracking_service.controller;

import org.springframework.web.bind.annotation.*;

import com.aiplatform.tracking_service.dto.DashboardResponse;
import com.aiplatform.tracking_service.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardResponse getSummary(
            @RequestHeader("Authorization") String authHeader) {

        return dashboardService.getSummary(authHeader);
    }
}
