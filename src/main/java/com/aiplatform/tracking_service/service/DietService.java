package com.aiplatform.tracking_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aiplatform.tracking_service.client.AuthClient;
import com.aiplatform.tracking_service.dto.DietRequest;
import com.aiplatform.tracking_service.entity.DietEntity;
import com.aiplatform.tracking_service.repository.DietRepository;
import com.aiplatform.tracking_service.entity.FoodEntity;
import com.aiplatform.tracking_service.repository.FoodRepository;

@Service
public class DietService {
    private final DietRepository repository;
    private final AuthClient authClient;
    private final FoodRepository foodRepository;

    public DietService(DietRepository repository, AuthClient authClient, FoodRepository foodRepository) {
        this.repository = repository;
        this.authClient = authClient;
        this.foodRepository = foodRepository;
    }

    public void addEntry(String authHeader, DietRequest request) {

        String userEmail = authClient.validateToken(authHeader);

        DietEntity entry = new DietEntity();

        entry.setUserEmail(userEmail);
        entry.setMealType(request.getMealType());
        entry.setServingSize(request.getServingSize());
        entry.setNumberOfServings(request.getNumberOfServings());
        entry.setTimestamp(LocalDateTime.now());

        if (request.getFoodId() != null) {

            FoodEntity food = foodRepository.findById(request.getFoodId())
                    .orElseThrow(() -> new RuntimeException("Food not found"));

            entry.setFoodId(food.getId());
            entry.setFoodName(food.getName());

            double multiplier = request.getNumberOfServings();

            entry.setCalories(food.getCalories() * multiplier);
            entry.setProtein(food.getProtein() * multiplier);
            entry.setCarbs(food.getCarbs() * multiplier);
            entry.setFat(food.getFat() * multiplier);
        }

        repository.save(entry);
    }

    public List<DietEntity> getEntries(String authHeader) {
        String userEmail = authClient.validateToken(authHeader);
        return repository.findByUserEmail(userEmail);
    }
}
