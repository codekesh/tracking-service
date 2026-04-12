package com.aiplatform.tracking_service.service;

import com.aiplatform.tracking_service.dto.FoodRequest;
import com.aiplatform.tracking_service.dto.FoodResponse;
import com.aiplatform.tracking_service.entity.FoodEntity;
import com.aiplatform.tracking_service.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodResponse addFood(FoodRequest request) {
        FoodEntity food = new FoodEntity();
        food.setName(request.getName());
        food.setCategory(request.getCategory());
        food.setServingSize(request.getServingSize());
        food.setServingUnit(request.getServingUnit());
        food.setCalories(request.getCalories());
        food.setProtein(request.getProtein());
        food.setCarbs(request.getCarbs());
        food.setFat(request.getFat());
        food.setActive(true);

        FoodEntity saved = foodRepository.save(food);
        return mapToResponse(saved);
    }

    public List<FoodResponse> getAllFoods() {
        return foodRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<FoodResponse> searchFoods(String search) {
        return foodRepository.findByNameContainingIgnoreCase(search)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FoodResponse mapToResponse(FoodEntity food) {
        return new FoodResponse(
                food.getId(),
                food.getName(),
                food.getCategory(),
                food.getServingSize(),
                food.getServingUnit(),
                food.getCalories(),
                food.getProtein(),
                food.getCarbs(),
                food.getFat(),
                food.getActive());
    }
}