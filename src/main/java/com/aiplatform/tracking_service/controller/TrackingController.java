package com.aiplatform.tracking_service.controller;

import com.aiplatform.tracking_service.dto.TrackingRequest;
import com.aiplatform.tracking_service.service.TrackingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/track")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping
    public void track(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TrackingRequest request) {

        trackingService.track(authHeader, request);
    }
}
