package com.aiplatform.tracking_service.controller;

import com.aiplatform.tracking_service.dto.TrackingRequest;
import com.aiplatform.tracking_service.entity.TrackingEntry;
import com.aiplatform.tracking_service.service.TrackingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public List<TrackingEntry> getAll(
            @RequestHeader("Authorization") String authHeader) {

        return trackingService.getAll(authHeader);
    }

    @PostMapping
    public void track(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TrackingRequest request) {

        trackingService.track(authHeader, request);
    }
}
