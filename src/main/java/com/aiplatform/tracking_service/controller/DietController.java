package com.aiplatform.tracking_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.aiplatform.tracking_service.dto.DietRequest;
import com.aiplatform.tracking_service.entity.DietEntity;
import com.aiplatform.tracking_service.service.DietService;

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
