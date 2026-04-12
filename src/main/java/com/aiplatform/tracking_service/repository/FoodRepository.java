package com.aiplatform.tracking_service.repository;

import com.aiplatform.tracking_service.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
    List<FoodEntity> findByNameContainingIgnoreCase(String name);
}