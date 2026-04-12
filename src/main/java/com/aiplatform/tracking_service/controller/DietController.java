package com.aiplatform.tracking_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.aiplatform.tracking_service.dto.DietRequest;
import com.aiplatform.tracking_service.entity.DietEntity;
import com.aiplatform.tracking_service.service.DietService;

@RestController
@RequestMapping("/tracking/diet")
public class DietController {
    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @PostMapping
    public void addEntry(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody DietRequest request) {
        dietService.addEntry(authHeader, request);
    }

    @GetMapping
    public List<DietEntity> getEntries(
            @RequestHeader("Authorization") String authHeader) {

        return dietService.getEntries(authHeader);
    }
}
