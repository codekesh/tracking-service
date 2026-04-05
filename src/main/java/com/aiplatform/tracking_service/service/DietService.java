package com.aiplatform.tracking_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aiplatform.tracking_service.client.AuthClient;
import com.aiplatform.tracking_service.dto.DietRequest;
import com.aiplatform.tracking_service.entity.DietEntity;
import com.aiplatform.tracking_service.repository.DietRepository;

@Service
public class DietService {
    private final DietRepository repository;
    private final AuthClient authClient;

    public DietService(DietRepository repository, AuthClient authClient) {
        this.repository = repository;
        this.authClient = authClient;
    }

    public void addEntry(String authHeader, DietRequest request) {

        String userEmail = authClient.validateToken(authHeader);

        DietEntity entry = new DietEntity();

        entry.setUserEmail(userEmail);
        entry.setFoodName(request.getFoodName());
        entry.setMealType(request.getMealType());
        entry.setServingSize(request.getServingSize());
        entry.setNumberOfServings(request.getNumberOfServings());
        entry.setTimestamp(LocalDateTime.now());

        repository.save(entry);
    }

    public List<DietEntity> getEntries(String authHeader) {
        String userEmail = authClient.validateToken(authHeader);
        return repository.findByUserEmail(userEmail);
    }
}
