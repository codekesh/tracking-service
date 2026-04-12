package com.aiplatform.tracking_service.controller;

import com.aiplatform.tracking_service.dto.FoodRequest;
import com.aiplatform.tracking_service.dto.FoodResponse;
import com.aiplatform.tracking_service.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public FoodResponse addFood(@RequestBody FoodRequest request) {
        return foodService.addFood(request);
    }

    @GetMapping
    public List<FoodResponse> getFoods(@RequestParam(required = false) String search) {
        if (search != null && !search.trim().isEmpty()) {
            return foodService.searchFoods(search);
        }
        return foodService.getAllFoods();
    }
}