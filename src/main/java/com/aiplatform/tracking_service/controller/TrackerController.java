package com.aiplatform.tracking_service.controller;

import com.aiplatform.tracking_service.dto.TrackerRequest;
import com.aiplatform.tracking_service.entity.TrackerEntity;
import com.aiplatform.tracking_service.service.TrackerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trackers")
public class TrackerController {

    private final TrackerService trackerService;

    public TrackerController(TrackerService trackerService) {
        this.trackerService = trackerService;
    }

    @GetMapping
    public List<TrackerEntity> getAll(
            @RequestHeader("Authorization") String authHeader) {

        return trackerService.getAll(authHeader);
    }

    @PostMapping
    public void createTracker(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TrackerRequest request) {

        trackerService.createTracker(authHeader, request);
    }
}